package com.safetynet.alert.service.records;

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
import com.safetynet.alert.DAO.MedicalRecordsDAO;
import com.safetynet.alert.IO.SourceReader;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.factory.SafetynetalertFactory;

@Service
public class MedicalRecordsDaoLoaderService {

	static final Logger medicalRecordsDAOLoaderServiceLogger = LogManager.getLogger("MedicalRecordsDAOLoaderService");
	
	@Autowired
	MedicalRecordsDAO medicalRecordsDAOMedicalRecordsDAOLoaderService;
	
	@Autowired
	SourceReader sourceReaderMedicalRecordsDAOLoaderService;
	
	@Autowired
	SafetynetalertFactory safetynetalertFactoryMedicalRecordsDAOLoaderService;
	
	MedicalRecordsDaoLoaderService(MedicalRecordsDAO medicalRecordsDAOMedicalRecordsDAOLoaderService
			, SafetynetalertFactory safetynetalertFactoryMedicalRecordsDAOLoaderService
			, SourceReader sourceReaderMedicalRecordsDAOLoaderService){
		this.medicalRecordsDAOMedicalRecordsDAOLoaderService = medicalRecordsDAOMedicalRecordsDAOLoaderService;
		this.safetynetalertFactoryMedicalRecordsDAOLoaderService = safetynetalertFactoryMedicalRecordsDAOLoaderService;
		this.sourceReaderMedicalRecordsDAOLoaderService = sourceReaderMedicalRecordsDAOLoaderService;
	}

	public void stringToDAO( ) {
		
		String jsonString = sourceReaderMedicalRecordsDAOLoaderService.jsonToString();

		String medicalrecordsJsonArrayString = jsonString.substring(
				jsonString.indexOf('[', jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1),
				jsonString.indexOf('}', jsonString.length() - 2));
		medicalRecordsDAOLoaderServiceLogger
				.trace("json medicalrecords array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = safetynetalertFactoryMedicalRecordsDAOLoaderService.getObjectMapper();

		MedicalRecords[] medicalrecordsJavaArray = null;
		try {
			medicalrecordsJavaArray = objectMapper.readValue(medicalrecordsJsonArrayString, MedicalRecords[].class);
			medicalRecordsDAOLoaderServiceLogger.info("json medicalrecords array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			medicalRecordsDAOLoaderServiceLogger
					.error("JsonMappingException, json medicalrecords array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			medicalRecordsDAOLoaderServiceLogger.error(
					"JsonProcessingException, json medicalrecords array as string can't be processed to java array");

		}
		
		if (Objects.nonNull(medicalrecordsJavaArray)) {
		List<MedicalRecords> medicalrecordsJavaList = Arrays.asList(medicalrecordsJavaArray);
		medicalRecordsDAOMedicalRecordsDAOLoaderService.saveAllMedicalRecords(medicalrecordsJavaList);
		medicalRecordsDAOLoaderServiceLogger.info("json file medicalrecords content saved into MedicalRecordsDAO");
		} else {
			medicalRecordsDAOLoaderServiceLogger.debug("json file medicalrecords content is null");
		}

	}
	
}
