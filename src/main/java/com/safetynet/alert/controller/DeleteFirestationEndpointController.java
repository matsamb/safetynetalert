package com.safetynet.alert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.service.firestations.DeleteFirestationByAddressService;

@RestController
public class DeleteFirestationEndpointController {

	static final Logger deleteFirestationEndpointControllerLogger = LogManager.getLogger("DeleteFirestationEndpointController");
	
	@Autowired 
	DeleteFirestationByAddressService deleteFirestationByAddressServiceDeleteFirestationEndpointController;
	
	DeleteFirestationEndpointController(DeleteFirestationByAddressService deleteFirestationByAddressServiceDeleteFirestationEndpointController){
		this.deleteFirestationByAddressServiceDeleteFirestationEndpointController = deleteFirestationByAddressServiceDeleteFirestationEndpointController;
	}
	
	@DeleteMapping("firestation")//?address=<address>
	public void deleteFirestation(@RequestParam String address) {
		deleteFirestationByAddressServiceDeleteFirestationEndpointController.deleteByAddress(address);
		deleteFirestationEndpointControllerLogger.info("Firestations cover on "+address+" deleted");
	}
	
}
