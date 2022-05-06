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

import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.service.firestations.SaveFirestationService;

@RestController
public class PostFirestationEndpointController {

	static final Logger postFirestationEndpointControllerLogger = LogManager.getLogger("PostFirestationEndpointController");
	
	@Autowired 
	SaveFirestationService saveFirestationServicePostFirestationEndpointController;
	
	PostFirestationEndpointController(SaveFirestationService saveFirestationServicePostFirestationEndpointController){
		this.saveFirestationServicePostFirestationEndpointController = saveFirestationServicePostFirestationEndpointController;
	}
	
	@PostMapping("firestation")
	public ResponseEntity<Firestations> addFirestations(@RequestBody Firestations firestation) {
		saveFirestationServicePostFirestationEndpointController.saveFirestation(firestation);
		Firestations addedFirestation = firestation;
		
		if (Objects.isNull(addedFirestation)) {
			postFirestationEndpointControllerLogger.error("the posted firestation request body is empty");
			return ResponseEntity.noContent().build();
		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{stationAddress}")
					.buildAndExpand(addedFirestation.getAddress()).toUri();
			postFirestationEndpointControllerLogger.debug("Posted firestation "+addedFirestation.getAddress()+" URI created");
			postFirestationEndpointControllerLogger.info("Posted firestation "+addedFirestation.getAddress()+" created");
			return ResponseEntity.created(location).build();
		}
	}
	
}
