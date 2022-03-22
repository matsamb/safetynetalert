package com.safetynet.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.repository.SafetynetalertDataTablesEntityNameRepository;

@Service
public class JsonSourceLoaderToDatabaseService {

	@Autowired
	SafetynetalertDataTablesEntityNameRepository SafetynetalertDataTablesEbtityNameSourceServiceRepository;
	
	@Autowired
	SafetynetalertFactory jsonSourceLoaderToDatabaseServiceFactory;
	
	public JsonSourceLoaderToDatabaseService (SafetynetalertDataTablesEntityNameRepository SafetynetalertDataTablesEbtityNameSourceServiceRepository) {
		this.SafetynetalertDataTablesEbtityNameSourceServiceRepository = SafetynetalertDataTablesEbtityNameSourceServiceRepository;
	}
	
	
	
}
