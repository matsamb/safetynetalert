package com.safetynet.alert.factory;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SafetynetalertFactory {

	//@Bean
	public Resource loadSafetynetAlertDataWithClassPathResource() {
	    return new ClassPathResource("/rest.json");
	}
	
	public TypeReference<Map<String,Object>> getTypeReferenceMap() {
		return new TypeReference<>() {};
	}
	
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
