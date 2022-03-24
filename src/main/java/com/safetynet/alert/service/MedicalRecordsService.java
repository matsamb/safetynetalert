package com.safetynet.alert.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {
	
	static final Logger medicalRecordsServiceLogger = LogManager.getLogger("medicalRecordsService");
	
	@Autowired
	SafetynetalertFactory medicalRecordsServiceFactory;
	
	@Autowired 
	MedicalRecordsRepository medicalRecordsServiceRepository;
	
	public MedicalRecordsService (MedicalRecordsRepository medicalRecordsServiceRepository){
		this.medicalRecordsServiceRepository = medicalRecordsServiceRepository;
	}
	
	public void jsonToDatabase() {

		Resource resource = medicalRecordsServiceFactory.loadSafetynetAlertDataWithClassPathResource();
		List<String> jsonAsListOfStrings = null;
		
		try (BufferedReader finalReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());// .forEach(System.out::println);
			medicalRecordsServiceLogger.info("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			medicalRecordsServiceLogger.error("file reader service can not fetch file content");
		}
			
		String jsonString = String.join("", jsonAsListOfStrings);
		medicalRecordsServiceLogger.trace("json file content turned into a java string");
/*
		String personsJsonArrayString = jsonString.substring(jsonString.indexOf('['), jsonString.indexOf(']') + 1);
		//System.out.println(personsJsonArrayString);
		medicalRecordsServiceLogger.trace("json persons array as string correctly extracted from json source file string");

		String firestationsJsonArrayString = jsonString.substring(jsonString.indexOf('[', jsonString.indexOf(']')),
					jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1);
		//System.out.println(firestationsJsonArrayString); 
		medicalRecordsServiceLogger.trace("json firestations array as string correctly extracted from json source file string");
*/		
		String medicalrecordsJsonArrayString = jsonString.substring(
					jsonString.indexOf('[', jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1),
					jsonString.indexOf('}', jsonString.length() - 2));
		//System.out.println(medicalrecordsJsonArrayStringWithPossibleEmptyEmbeddedarrays);
		medicalRecordsServiceLogger.trace("json medical records array as string correctly extracted from json source file string");
 		
		/*String medicalrecordsJsonArrayString = medicalrecordsJsonArrayStringWithPossibleEmptyEmbeddedarrays
				.replaceAll("\"allergies\":\\[\\]","\"allergies\":\\[\"allergy:N_A\"\\]")
				.replaceAll("\"medications\":\\[\\]","\"medications\":\\[\"medication:N_A\"\\]")
				.replaceAll("\"medications\":\\[\"aznol:350mg\",\"hydrapermazol:100mg\"\\]","\"medications\":\\[\"medications\":\"aznol_350\"\\,\"medications\":\"hydrapermazol_350mg\",\\]")
				//.replaceAll("\"allergies\":\\[\"nillacilan\"\\]","\"allergies\":\\[\"allergy:nillacilan\"\\]")
				.replaceAll("\"allergies\":\\[\"peanut\"\\]","\"allergies\":\\[\"allergy:peanut\"\\]")
				.replaceAll("\"allergies\":\\[\"xilliathal\"\\]","\"allergies\":\\[\"allergy:illisoxian\"\\]")
				;*/
		System.out.println(medicalrecordsJsonArrayString);
		
		ObjectMapper objectMapper = new ObjectMapper();

		MedicalRecords[] medicalRecordsJavaArray = null;
		try {
			medicalRecordsJavaArray = objectMapper.readValue(medicalrecordsJsonArrayString, MedicalRecords[].class);
			medicalRecordsServiceLogger.info("json medical records array as string correctly mapped to java array");

		} catch(JsonMappingException m) {
			m.printStackTrace();
			medicalRecordsServiceLogger.error("JsonMappingException, json medical records array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			medicalRecordsServiceLogger.error("JsonProcessingException, json medical records array as string can't be mapped to java array");

		} 
		List<MedicalRecords> medicalRecordsJavaList = Arrays.asList(medicalRecordsJavaArray);
		medicalRecordsJavaList.forEach(System.out::println);
		medicalRecordsServiceRepository.saveAll(medicalRecordsJavaList);
		medicalRecordsServiceLogger.info("json file medical records content loaded into database");

		
	}
	
	public void saveAllMedicalRecords(Iterable<MedicalRecords> medicalRecords) {
		medicalRecordsServiceRepository.saveAll(medicalRecords);
	}

	public Iterable<MedicalRecords> getAllMedicalRecords(){
		return medicalRecordsServiceRepository.findAll();
	}
	
}
