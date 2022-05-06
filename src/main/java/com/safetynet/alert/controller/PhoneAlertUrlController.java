package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByStationService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;

@RestController
public class PhoneAlertUrlController {

	static final Logger phoneAlertUrlControllerLogger = LogManager.getLogger("PhoneAlertUrlController");
	
	@Autowired
	FindFirestationByStationService findFirestationByStationServicePhoneAlertUrlController;
	
	@Autowired
	FindPersonByAddressService findPersonByAddressServicePhoneAlertUrlController;
	
	PhoneAlertUrlController(FindFirestationByStationService findFirestationByStationServicePhoneAlertUrlController
							, FindPersonByAddressService findPersonByAddressServicePhoneAlertUrlController){
		this.findFirestationByStationServicePhoneAlertUrlController = findFirestationByStationServicePhoneAlertUrlController;
		this.findPersonByAddressServicePhoneAlertUrlController = findPersonByAddressServicePhoneAlertUrlController;
	}
	
	@GetMapping("phoneAlert")//?station=<station>
	public Iterable<String> findPhoneAlertUrl(Integer station){
		List<String> responseList = new ArrayList<>(); 
		
		for(Firestations f: findFirestationByStationServicePhoneAlertUrlController.findByStation(station)){
			for(Persons p:findPersonByAddressServicePhoneAlertUrlController.findPersonByAddress(f.getAddress())){

				responseList.add(p.getPhone());
				phoneAlertUrlControllerLogger.debug("PhoneNumber added to responseList");
			}
		}
		phoneAlertUrlControllerLogger.info("PhoneAlert for firestation number "+station+" details displayed");
		return responseList;
	}
	
}
