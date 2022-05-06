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
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.exceptions.SafetynetalertNotFoundException;
import com.safetynet.alert.service.firestations.FindFirestationByAddressService;
import com.safetynet.alert.service.firestations.SaveFirestationService;

@RestController
public class PutFirestationEndpointController {

	static final Logger putFirestationEndpointControllerLogger = LogManager.getLogger("PutFirestationEndpointController");
	
	@Autowired
	SaveFirestationService saveFirestationServicePutFirestationEndpointController;
	
	@Autowired
	FindFirestationByAddressService findFirestationByAddressServicePutFirestationEndpointController;
	
	PutFirestationEndpointController( SaveFirestationService saveFirestationServicePutFirestationEndpointController
									 ,FindFirestationByAddressService findFirestationByAddressServicePutFirestationEndpointController	){
		this.saveFirestationServicePutFirestationEndpointController = saveFirestationServicePutFirestationEndpointController;
		this.findFirestationByAddressServicePutFirestationEndpointController = findFirestationByAddressServicePutFirestationEndpointController;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("firestation")//?address=<address>
	public ResponseEntity<Firestations> updateFirestation(@RequestParam String address, @RequestBody Firestations newFirestationDetails) {

		
		Firestations existingFirestation = findFirestationByAddressServicePutFirestationEndpointController.findByAddress(address);
		
		if(Objects.isNull(existingFirestation)) {
			throw new SafetynetalertNotFoundException("Firestation number "+address+", does not exist.");
		}else {
			
			putFirestationEndpointControllerLogger.info("Firestation number "+address+ " found");
			
			existingFirestation.setStation(newFirestationDetails.getStation());
			existingFirestation.setAddress(newFirestationDetails.getAddress());
			
			saveFirestationServicePutFirestationEndpointController.saveFirestation(existingFirestation);
			final Firestations updatedFirestation = existingFirestation;
			putFirestationEndpointControllerLogger.info("Firestation number "+address+ " updated");

			return ResponseEntity.ok(updatedFirestation);
	
		}
	}
	
}
