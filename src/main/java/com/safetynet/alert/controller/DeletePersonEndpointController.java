package com.safetynet.alert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.service.persons.DeletePersonByFirstNameAndLastNameService;

@RestController
public class DeletePersonEndpointController {

	static final Logger deletePersonEndpointControllerLogger = LogManager.getLogger("DeletePersonEndpointController");
	
	@Autowired
	DeletePersonByFirstNameAndLastNameService deletePersonByFirstNameAndLastNameServiceDeletePersonEndpointController;
	
	DeletePersonEndpointController(DeletePersonByFirstNameAndLastNameService deletePersonByFirstNameAndLastNameServiceDeletePersonEndpointController){
		this.deletePersonByFirstNameAndLastNameServiceDeletePersonEndpointController = deletePersonByFirstNameAndLastNameServiceDeletePersonEndpointController;
	}
	
	@DeleteMapping("person")//?firstName=<firstName>&lastName=<lastName>")
	public void deleteOnePerson(@RequestParam String firstName, @RequestParam String lastName) {
		deletePersonByFirstNameAndLastNameServiceDeletePersonEndpointController.deleteByFirstNameAndLastName(firstName, lastName);
		deletePersonEndpointControllerLogger.info("Person "+ firstName+" "+lastName+" deleted");
	}

}
