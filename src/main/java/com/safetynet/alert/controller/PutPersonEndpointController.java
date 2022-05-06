package com.safetynet.alert.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.exceptions.SafetynetalertNotFoundException;
import com.safetynet.alert.service.persons.FindPersonByFirstNameAndLastNameService;
import com.safetynet.alert.service.persons.SavePersonService;

@RestController
public class PutPersonEndpointController {

	static final Logger putPersonEndpointControllerLogger = LogManager.getLogger("PutPersonEndpointController");
	
	@Autowired
	SavePersonService savePersonServicePutPersonEndpointController;
	
	@Autowired
	FindPersonByFirstNameAndLastNameService findPersonByFirstNameAndLastNameServicePutPersonEndpointController;
	
	PutPersonEndpointController(SavePersonService savePersonServicePutPersonEndpointController
								,FindPersonByFirstNameAndLastNameService findPersonByFirstNameAndLastNameServicePutPersonEndpointController){
		this.findPersonByFirstNameAndLastNameServicePutPersonEndpointController = findPersonByFirstNameAndLastNameServicePutPersonEndpointController;
		this.savePersonServicePutPersonEndpointController = savePersonServicePutPersonEndpointController;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("person")//?firstName=<firstName>&lastName=<lastName>")
	public ResponseEntity<Persons> upDatePersons(@RequestParam String firstName, @RequestParam String lastName,
			@RequestBody Persons newPersonDetails) {

		Persons existingPerson = findPersonByFirstNameAndLastNameServicePutPersonEndpointController.findPersonsByFirstNameAndLastName(firstName, lastName);
		
		if (Objects.isNull(existingPerson)) {
			throw new SafetynetalertNotFoundException("Person " + firstName + " " + lastName + " is not registered");

		} else {

			putPersonEndpointControllerLogger.info("Person " + firstName + " " + lastName + " found");
			
			existingPerson.setAddress(newPersonDetails.getAddress());
			existingPerson.setCity(newPersonDetails.getCity());
			existingPerson.setZip(newPersonDetails.getZip());
			existingPerson.setPhone(newPersonDetails.getPhone());
			existingPerson.setEmail(newPersonDetails.getEmail());
			
			savePersonServicePutPersonEndpointController.savePersons(existingPerson);
			final Persons updatePerson = existingPerson;
			putPersonEndpointControllerLogger.info("Person " + firstName + " " + lastName + " updated");
			return ResponseEntity.ok(updatePerson);
		}
	}
}
