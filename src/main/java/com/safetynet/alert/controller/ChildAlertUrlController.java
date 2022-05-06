package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.DTO.ChildAlertAgregDTO;
import com.safetynet.alert.DTO.ChildAlertDTO;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.persons.FindPersonByAddressService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;

@RestController
public class ChildAlertUrlController {

	static final Logger childAlertUrlControllerLogger = LogManager.getLogger("ChildAlertUrlControllerLogger");
	
	@Autowired
	FindPersonByAddressService findPersonByAddressServiceChildAlertUrlController;
	
	@Autowired
	FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceChildAlertUrlController;
	
	ChildAlertUrlController(FindPersonByAddressService findPersonByAddressServiceChildAlertUrlController
						,FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceChildAlertUrlController){
		this.findPersonByAddressServiceChildAlertUrlController = findPersonByAddressServiceChildAlertUrlController;
		this.findMedicalRecordsByFirstNameAndLastNameServiceChildAlertUrlController = findMedicalRecordsByFirstNameAndLastNameServiceChildAlertUrlController;
	}
	
	@GetMapping("childAlert")//?address=<address>
	public ChildAlertAgregDTO findChildAlertUrl(@RequestParam String address){
		ChildAlertAgregDTO result = new ChildAlertAgregDTO();
		List<ChildAlertDTO> children = new ArrayList<>();
		ChildAlertDTO child = new ChildAlertDTO();
		List<ChildAlertDTO> adults = new ArrayList<>();
		ChildAlertDTO adult = new ChildAlertDTO();
		Calendar lendar = Calendar.getInstance();
		lendar.setTimeInMillis(System.currentTimeMillis()); 
		
		for (Persons p :findPersonByAddressServiceChildAlertUrlController.findPersonByAddress(address)) {
			childAlertUrlControllerLogger.debug("loop on found persons at "+address);

			for (MedicalRecords m :findMedicalRecordsByFirstNameAndLastNameServiceChildAlertUrlController.findByFirstNameAndLastName(p.getFirstName(), p.getLastName()) ) {
				childAlertUrlControllerLogger.debug("loop on found medical records ");

				String[] date = m.getBirthDate().split("/");
				
				int y = lendar.get(Calendar.YEAR) - Integer.parseInt(date[2]);//Year
				int mo = lendar.get(Calendar.MONTH) - Integer.parseInt(date[0]);//month
				int d = lendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(date[1]);//day
				
				if (y<18 || y == 18 && mo<0 || y == 18 && mo == 0 && d<0) {
					child.setFirstName(m.getFirstName());
					child.setLastName(m.getLastName());
					child.setAge(y);
					children.add((ChildAlertDTO) child.clone());
					childAlertUrlControllerLogger.debug("child added");
				}else{
					adult.setFirstName(m.getFirstName());
					adult.setLastName(m.getLastName());
					adult.setAge(y);
					adults.add((ChildAlertDTO) adult.clone());
					childAlertUrlControllerLogger.debug("adult added");
				}			
			}
		}
		if(children.size()>0) {
			result.setChild(List.copyOf(children));
			result.setAdult(List.copyOf(adults));
			childAlertUrlControllerLogger.info("ChildAlert URL details for "+address + " displayed");
		}else {
			childAlertUrlControllerLogger.info("No child at "+address );
		}
		
		return (ChildAlertAgregDTO)result.clone();
	}
	
}
