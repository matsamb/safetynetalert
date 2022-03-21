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

		try (BufferedReader finalReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			List<String> jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());// .forEach(System.out::println);
			serviceLogger.info("json file reader operates correctly");

			String jsonString = String.join("", jsonAsListOfStrings);
			serviceLogger.trace("json file content turned into a java string");

			String personsJsonArrayString = jsonString.substring(jsonString.indexOf('['), jsonString.indexOf(']') + 1);
			System.out.println(personsJsonArrayString);

			String firestationsJsonArrayString = jsonString.substring(jsonString.indexOf('[', jsonString.indexOf(']')),
					jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1);
			System.out.println(jsonString.indexOf('[', jsonString.indexOf(']')) + "  "
					+ jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1 + "  " + firestationsJsonArrayString);

			String medicalrecordsJsonArrayString = jsonString.substring(
					jsonString.indexOf('[', jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1),
					jsonString.indexOf('}', jsonString.length() - 2));
			System.out.println(medicalrecordsJsonArrayString);

			ObjectMapper objectMapper = new ObjectMapper();

			Persons[] personsJavaArray = objectMapper.readValue(personsJsonArrayString, Persons[].class);
			List<Persons> personsJavaList = Arrays.asList(personsJavaArray);
			safetynetalertPersonsRepository.saveAll(personsJavaList);

			serviceLogger.trace("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			serviceLogger.error("file reader service can not fetch file content");
		}
	}

	public void saveAllPersons(Iterable<Persons> persons) {
		safetynetalertPersonsRepository.saveAll(persons);
	}

}
