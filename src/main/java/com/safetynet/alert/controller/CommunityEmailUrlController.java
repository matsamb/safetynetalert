package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.service.persons.FindPersonsByCityService;

@RestController
public class CommunityEmailUrlController {

	static final Logger communityEmailUrlControllerLogger = LogManager.getLogger("CommunityEmailUrlControllerLogger");
	
	@Autowired
	FindPersonsByCityService findPersonsByCityServiceCommunityEmailUrlController;
	
	CommunityEmailUrlController(FindPersonsByCityService findPersonsByCityServiceCommunityEmailUrlController){
		this.findPersonsByCityServiceCommunityEmailUrlController = findPersonsByCityServiceCommunityEmailUrlController;
	}
	
	@GetMapping("communityEmail")//?city=<city>"
	public Iterable<String> findCommunityEmailUrl(@RequestParam String city){
		List<String> resultList = new ArrayList<>();
		findPersonsByCityServiceCommunityEmailUrlController
				.findByCity(city)
				.forEach(e -> 
					resultList.add(e.getEmail())
				);
		communityEmailUrlControllerLogger.info(city+" CommunityEmailUrl displayed");
		return resultList;
	}
}
