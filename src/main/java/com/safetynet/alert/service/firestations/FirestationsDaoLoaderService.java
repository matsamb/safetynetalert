package com.safetynet.alert.service.firestations;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.DAO.FirestationsDAO;
import com.safetynet.alert.IO.SourceReader;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.factory.SafetynetalertFactory;

@Service
public class FirestationsDaoLoaderService {

	static final Logger stationsLogger = LogManager.getLogger("FirestationsDaoLoaderService");
	
	@Autowired
	FirestationsDAO stationsDAOService;
	
	@Autowired
	SourceReader firestationFileReader;
	
	@Autowired
	SafetynetalertFactory sationsFactory;
	
	FirestationsDaoLoaderService(FirestationsDAO stationsDAOService
						,SafetynetalertFactory sationsFactory
						,SourceReader firestationFileReader){
		this.stationsDAOService = stationsDAOService;
		this.sationsFactory = sationsFactory;
		this.firestationFileReader = firestationFileReader;
	}

	public void stringToDAO( ) {
		
		String jsonString = firestationFileReader.jsonToString();

		String firestationsJsonArrayString = jsonString.substring(jsonString.indexOf('[', jsonString.indexOf(']')),
				jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1);
		stationsLogger
				.trace("json firestations array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = sationsFactory.getObjectMapper();

		Firestations[] firestationsJavaArray = null;
		try {
			firestationsJavaArray = objectMapper.readValue(firestationsJsonArrayString, Firestations[].class);
			stationsLogger.info("json firestations array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			stationsLogger
					.error("JsonMappingException, json firestations array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			stationsLogger.error(
					"JsonProcessingException, json firestations array as string can't be processed to java array");

		}
		
		if (Objects.nonNull(firestationsJavaArray)) {
		List<Firestations> firestationsJavaList = Arrays.asList(firestationsJavaArray);
		stationsDAOService.saveAllFirestations(firestationsJavaList);
		stationsLogger.info("json file firestations content loaded into database");
		}else {
			stationsLogger.debug("json file firestations content is null");
		}
	}

}
