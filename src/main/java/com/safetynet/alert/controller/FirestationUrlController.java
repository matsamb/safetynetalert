package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.DTO.StationNumberDTO;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByStationService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;

@RestController
public class FirestationUrlController {

	static final Logger firestationUrlLogger = LogManager.getLogger("FirestationUrlController");
	
	@Autowired
	FindPersonByAddressService findPersonByAddressServiceFirestationUrlController;
	
	@Autowired
	FindFirestationByStationService findFirestationByStationServiceFirestationUrlController;
	
	FirestationUrlController(FindPersonByAddressService findPersonByAddressServiceFirestationUrlController
				, FindFirestationByStationService findFirestationByStationServiceFirestationUrlController){
		this.findPersonByAddressServiceFirestationUrlController = findPersonByAddressServiceFirestationUrlController;
		this.findFirestationByStationServiceFirestationUrlController = findFirestationByStationServiceFirestationUrlController;
	}
	
	
	@GetMapping("firestation")//?stationNumber=<station>
	public Iterable<StationNumberDTO> findFirestationUrl(@RequestParam Integer station){
		List<StationNumberDTO> responseList = new ArrayList<>(); 
		StationNumberDTO responseEntity = new StationNumberDTO();
		
		for(Firestations f: findFirestationByStationServiceFirestationUrlController.findByStation(station)){
			firestationUrlLogger.debug("Firestation "+station+" found");
			for(Persons p:findPersonByAddressServiceFirestationUrlController.findPersonByAddress(f.getAddress())){
				
				responseEntity.setFirstName(p.getFirstName());
				responseEntity.setLastName(p.getLastName());
				responseEntity.setAddress(p.getAddress());
				responseEntity.setPhone(p.getPhone());
				responseList.add((StationNumberDTO)responseEntity.clone());
				firestationUrlLogger.debug("responseEntity added to responseList");
			}
		}
		firestationUrlLogger.info("Firestation number "+station+" details displayed");
		return responseList;
	}
	
}
