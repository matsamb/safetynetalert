package com.safetynet.alert.controller;

import java.net.URI;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.persons.SavePersonService;

@RestController
public class PostPersonEndpointController {

	static final Logger PostPersonEndpointControllerLogger = LogManager.getLogger("PostPersonEndpointController");
	
	@Autowired
	SavePersonService savePersonServicePostPersonEndpointController;
	
	PostPersonEndpointController(SavePersonService savePersonServicePostPersonEndpointController){
		this.savePersonServicePostPersonEndpointController = savePersonServicePostPersonEndpointController;
	}
	
	@PostMapping("person")
	public ResponseEntity<Persons> addPersons(@RequestBody Persons persons) {
		savePersonServicePostPersonEndpointController.savePersons(persons);
		Persons addedPerson = persons; 
		PostPersonEndpointControllerLogger.debug("Posted person recovered");
		
		if (Objects.isNull(addedPerson)) {
			PostPersonEndpointControllerLogger.error("Posted person request body is empty");
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
				.buildAndExpand(addedPerson.getFirstName(), addedPerson.getLastName()).toUri();
		PostPersonEndpointControllerLogger.debug("Posted person "+ addedPerson.getFirstName()+" "+addedPerson.getLastName()+" URI created");
		PostPersonEndpointControllerLogger.info("Posted person "+ addedPerson.getFirstName()+" "+addedPerson.getLastName()+" created");
		return ResponseEntity.created(location).build();

	}
	
}
