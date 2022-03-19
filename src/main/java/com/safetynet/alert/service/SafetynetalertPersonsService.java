package com.safetynet.alert.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.repository.SafetynetalertPersonsRepository;

@Service
public class SafetynetalertPersonsService {

	static final Logger logger = LogManager.getLogger("safetynetalertService");
	
	@Autowired
	SafetynetalertPersonsRepository safetynetalertPersonsRepository;
	
	@Autowired
	SafetynetalertFactory serviceFactory;
	
	public SafetynetalertPersonsService (SafetynetalertPersonsRepository safetynetalertRepository) {
		this.safetynetalertPersonsRepository = safetynetalertRepository;
	}
	

	public void jsonToDatabase() {
		
		var resource = serviceFactory.loadSafetynetAlertDataWithClassPathResource();
		//resource.getFile();
		try (var firstReader = new InputStreamReader(resource.getInputStream());
				var finalReader = new BufferedReader(firstReader)
				) {
			
			List<String> jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());//.forEach(System.out::println);
			
			jsonAsListOfStrings.forEach(System.out::println);

			String jsonString = String.join("", jsonAsListOfStrings);

			System.out.println(jsonString);
			
			logger.trace("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("file reader service can not fetch file content");
			
	
	}

	}
	
	public void saveAllPersons(Iterable<Persons> persons){
		String jsonString = "";
		ObjectMapper dataTableNameMapper = serviceFactory.getObjectMapper();
		try {
			JsonNode dataTableNamesJsonNode = dataTableNameMapper.readValue(jsonString, JsonNode.class);
		}catch(IOException e) {
			e.printStackTrace();
		}
		safetynetalertPersonsRepository.saveAll(persons);		
	}

}
