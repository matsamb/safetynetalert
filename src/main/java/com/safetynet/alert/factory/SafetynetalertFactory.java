package com.safetynet.alert.factory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SafetynetalertFactory {
	
	public SafetynetalertFactory() {}
	
	//@Bean
	public Resource loadSafetynetAlertDataWithClassPathResource() {
	    return new ClassPathResource("/rest.json");
	}
	
	//@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	//@Bean
	public Resource loadSafetynetAlertTestDataWithClassPathResource() {
		return new ClassPathResource("/testFile.json");
	}

}
