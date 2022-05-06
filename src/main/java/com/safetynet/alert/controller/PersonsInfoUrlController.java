package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.DTO.PersonsInfoDTO;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.persons.FindPersonsByLastNameService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;

@RestController
public class PersonsInfoUrlController {

	static final Logger personsInfoUrlControllerLogger = LogManager.getLogger("PersonsInfoUrlController");
	
	@Autowired
	FindPersonsByLastNameService findPersonsByLastNameServicePersonsInfoUrlController;
	
	@Autowired
	FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServicePersonsInfoUrlController;
	
	PersonsInfoUrlController(FindPersonsByLastNameService findPersonsByLastNameServicePersonsInfoUrlController
							,FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServicePersonsInfoUrlController){
		this.findPersonsByLastNameServicePersonsInfoUrlController = findPersonsByLastNameServicePersonsInfoUrlController;
		this.findMedicalRecordsByFirstNameAndLastNameServicePersonsInfoUrlController = findMedicalRecordsByFirstNameAndLastNameServicePersonsInfoUrlController;
	}
	
	@GetMapping("personInfo")//?firstName=<firstName>&lastName=<lastName>
	public Iterable<PersonsInfoDTO> findPersonInfoUrl(@RequestParam String firstName, @RequestParam String lastName){
		List<PersonsInfoDTO> resultList = new ArrayList<>();
		PersonsInfoDTO result = new PersonsInfoDTO();
		Calendar lendar = Calendar.getInstance();
		lendar.setTimeInMillis(System.currentTimeMillis()); 
				
		for(Persons p:findPersonsByLastNameServicePersonsInfoUrlController.findByLastName(lastName)) {
			personsInfoUrlControllerLogger.debug("person "+p.getFirstName()+" "+lastName+" found");
			result.setFirstName(p.getFirstName());
			result.setLastName(p.getLastName());
			result.setAddress(p.getAddress());
			result.setEmail(p.getEmail());
			for(MedicalRecords m:List.copyOf(findMedicalRecordsByFirstNameAndLastNameServicePersonsInfoUrlController.findByFirstNameAndLastName(p.getFirstName(),p.getLastName()))) {
				personsInfoUrlControllerLogger.debug("records "+p.getFirstName()+" "+lastName+" found");

				String[] date = m.getBirthDate().split("/");
				
				int y = lendar.get(Calendar.YEAR) - Integer.parseInt(date[2]);//Year
				int mo = lendar.get(Calendar.MONTH) - Integer.parseInt(date[0]);//month
				int d = lendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(date[1]);//day
				if (mo<0 || mo == 0 && d<0) {
					result.setAge(y);
				}else {
					result.setAge(y+1);
				}
				
				result.setMedication(Arrays.asList(m.getMedications()));
				result.setAllergy(Arrays.asList(m.getAllergies()));
				
			}
			resultList.add((PersonsInfoDTO)result.clone());
		}
		
		personsInfoUrlControllerLogger.info("PersonInfo Url details for name "+lastName+" displayed");

		return resultList;
	}
	
}
