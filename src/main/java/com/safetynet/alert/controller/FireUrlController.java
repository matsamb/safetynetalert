package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.DTO.FireDTO;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByAddressService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;

@RestController
public class FireUrlController {

	static final Logger fireUrlControllerLogger = LogManager.getLogger("FireUrlControllerLogger");
	
	@Autowired
	FindPersonByAddressService findPersonByAddressServiceFireUrlController;
	
	@Autowired
	FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceFireUrlController;
	
	@Autowired
	FindFirestationByAddressService findFirestationByAddressServiceFireUrlController;
	
	FireUrlController(FindPersonByAddressService findPersonByAddressServiceFireUrlController
					,FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceFireUrlController
					,FindFirestationByAddressService findFirestationByAddressServiceFireUrlController){
		this.findPersonByAddressServiceFireUrlController = findPersonByAddressServiceFireUrlController;
		this.findMedicalRecordsByFirstNameAndLastNameServiceFireUrlController = findMedicalRecordsByFirstNameAndLastNameServiceFireUrlController;
		this.findFirestationByAddressServiceFireUrlController = findFirestationByAddressServiceFireUrlController;
	}
	
	@GetMapping("fire")//?address=<address>
	public Iterable<FireDTO> findFireUrl(@RequestParam String address){
		List<FireDTO> resultList = new ArrayList<>();
		FireDTO result = new FireDTO();
		
		Firestations f = (Firestations) findFirestationByAddressServiceFireUrlController.findByAddress(address).clone(); 
		fireUrlControllerLogger.debug("fetch station number") ;
		result.setStation(f.getStation());
		
		
		for(Persons p: findPersonByAddressServiceFireUrlController.findPersonByAddress(address)) {
			result.setFirstName(p.getFirstName());
			result.setLastName(p.getLastName());
			result.setPhone(p.getPhone());
			fireUrlControllerLogger.debug("persons"+p.getFirstName()+" "+p.getLastName()+" fetched");
			for(MedicalRecords m: findMedicalRecordsByFirstNameAndLastNameServiceFireUrlController.findByFirstNameAndLastName(p.getFirstName(), p.getLastName())) {
				
				result.setAllergy(Arrays.asList((Allergies[])m.getAllergies()));
				result.setMedication(Arrays.asList((Medications[])m.getMedications()));			
			}
			fireUrlControllerLogger.debug("persons"+p.getFirstName()+" "+p.getLastName()+" added");
			resultList.add((FireDTO) result.clone());
		}
		fireUrlControllerLogger.info("FireUrl for firestation number "+result.getStation()+" details displayed");
		return resultList;
	}
	
}
