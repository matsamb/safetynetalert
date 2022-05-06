package com.safetynet.alert.service.persons;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.IO.SourceReader;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.factory.SafetynetalertFactory;

@Service
public class PersonsDaoLoaderService {

	static final Logger PersonsDaoLoaderServiceLogger = LogManager.getLogger("PersonsDaoLoaderService");

	@Autowired
	PersonsDAO personsDAOPersonsDaoLoaderService;
	
	@Autowired
	SourceReader sourceReaderPersonsDaoLoaderService;// = new FileReaderIO();

	@Autowired
	SafetynetalertFactory safetynetalertFactoryPersonsDaoLoaderService;

	PersonsDaoLoaderService(PersonsDAO personsDAOPersonsDaoLoaderService
			, SafetynetalertFactory safetynetalertFactoryPersonsDaoLoaderService
			, SourceReader sourceReaderPersonsDaoLoaderService) {
		this.personsDAOPersonsDaoLoaderService = personsDAOPersonsDaoLoaderService;
		this.sourceReaderPersonsDaoLoaderService = sourceReaderPersonsDaoLoaderService;
		this.safetynetalertFactoryPersonsDaoLoaderService = safetynetalertFactoryPersonsDaoLoaderService;
	}

	public void stringToDAO( ) {
		
		String jsonString = sourceReaderPersonsDaoLoaderService.jsonToString();

		String personsJsonArrayString = jsonString.substring(jsonString.indexOf('['), jsonString.indexOf(']') + 1);
		PersonsDaoLoaderServiceLogger.trace("json persons array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = safetynetalertFactoryPersonsDaoLoaderService.getObjectMapper();

		Persons[] personsJavaArray = null;
		try {
			personsJavaArray = objectMapper.readValue(personsJsonArrayString, Persons[].class);
			PersonsDaoLoaderServiceLogger.info("json persons array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			PersonsDaoLoaderServiceLogger.error("JsonMappingException, json persons array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			PersonsDaoLoaderServiceLogger
					.error("JsonProcessingException, json persons array as string can't be processed to java array");

		}

		if (Objects.nonNull(personsJavaArray)) {
			List<Persons> personsJavaList = Arrays.asList(personsJavaArray);
			personsDAOPersonsDaoLoaderService.saveAllPersons(personsJavaList);
			PersonsDaoLoaderServiceLogger.info("json file persons content saved into PersonsDAO");
		} else {
			PersonsDaoLoaderServiceLogger.debug("json file persons content is null");
		}

	}

}
