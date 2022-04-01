package com.safetynet.alert.factory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SafetynetalertFactory {

	public Resource loadSafetynetAlertDataWithClassPathResource() {
	    return new ClassPathResource("/rest.json");
	}
	
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate();
	}
	
}
