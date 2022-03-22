package com.safetynet.alert.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.repository.SafetynetalertPersonsRepository;

@Service
public class SafetynetalertPersonsService {

	static final Logger serviceLogger = LogManager.getLogger("safetynetalertService");

	@Autowired
	SafetynetalertPersonsRepository safetynetalertPersonsRepository;

	@Autowired
	SafetynetalertFactory serviceFactory;

	public SafetynetalertPersonsService(SafetynetalertPersonsRepository safetynetalertRepository) {
		this.safetynetalertPersonsRepository = safetynetalertRepository;
	}

	public void jsonToDatabase() {

		Resource resource = serviceFactory.loadSafetynetAlertDataWithClassPathResource();
		List<String> jsonAsListOfStrings = null;
		
		try (BufferedReader finalReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());// .forEach(System.out::println);
			serviceLogger.info("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			serviceLogger.error("file reader service can not fetch file content");
		}
			
		String jsonString = String.join("", jsonAsListOfStrings);
		serviceLogger.trace("json file content turned into a java string");

		String personsJsonArrayString = jsonString.substring(jsonString.indexOf('['), jsonString.indexOf(']') + 1);
		System.out.println(personsJsonArrayString);
		serviceLogger.trace("json persons array as string correctly extracted from json source file string");

		String firestationsJsonArrayString = jsonString.substring(jsonString.indexOf('[', jsonString.indexOf(']')),
					jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1);
		System.out.println(firestationsJsonArrayString);
		serviceLogger.trace("json firestations array as string correctly extracted from json source file string");
		
		String medicalrecordsJsonArrayString = jsonString.substring(
					jsonString.indexOf('[', jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1),
					jsonString.indexOf('}', jsonString.length() - 2));
		System.out.println(medicalrecordsJsonArrayString);
		serviceLogger.trace("json medicalrecords array as string correctly extracted from json source file string");
		
		ObjectMapper objectMapper = new ObjectMapper();

		Persons[] personsJavaArray = null;
		try {
			personsJavaArray = objectMapper.readValue(personsJsonArrayString, Persons[].class);
			serviceLogger.info("json persons array as string correctly mapped to java array");

		} catch(JsonMappingException m) {
			m.printStackTrace();
			serviceLogger.error("JsonMappingException, json persons array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			serviceLogger.error("JsonProcessingException, json persons array as string can't be mapped to java array");

		} 
		List<Persons> personsJavaList = Arrays.asList(personsJavaArray);
		safetynetalertPersonsRepository.saveAll(personsJavaList);
		serviceLogger.info("json file persons content loaded into database");

		
	}

	public void saveAllPersons(Iterable<Persons> persons) {
		safetynetalertPersonsRepository.saveAll(persons);
	}

	public Iterable<Persons> getAllPersons(){
		return safetynetalertPersonsRepository.findAll();
	}
}
