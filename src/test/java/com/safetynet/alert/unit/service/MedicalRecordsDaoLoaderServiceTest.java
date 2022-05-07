package com.safetynet.alert.unit.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.DAO.MedicalRecordsDAO;
import com.safetynet.alert.IO.SourceReader;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsDaoLoaderServiceTest {

	@Mock
	private MedicalRecordsDAO medicalRecordsDAO;
	@Mock
	private SourceReader sourceReader;
	@Mock
	private SafetynetalertFactory safetynetalertFactory;
	
	@InjectMocks
	private MedicalRecordsDaoLoaderService medicalRecordsDaoLoaderService;
	
	@BeforeEach
	public void init() {
		
		ObjectMapper oMapper = new ObjectMapper();

		String s = "{\r\n"
				+ " \"persons\": [\r\n"
				+ " { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },\r\n"
				+ " { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6513\", \"email\":\"drk@email.com\" }"
				+ "], \r\n"
				+ " \"firestations\": [\r\n"
				+ "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }"
				+ "],\r\n"
				+ " \"medicalrecords\": [\r\n"
				+ " { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\r\n"
				+ " { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] }"
				+ "] \r\n"
				+ "}";
		
		
		when(safetynetalertFactory.getObjectMapper()).thenReturn(oMapper);
		when(sourceReader.jsonToString()).thenReturn(s);
		
		medicalRecordsDaoLoaderService.stringToDAO();
	}
	
	@Test
	public void givenAjsonString_WhenStringToDaoIsCalled_ThenSaveAllMedicalRecordsShouldBeUsedOnce() {
		
		verify(medicalRecordsDAO, times(1)).saveAllMedicalRecords(anyList());

	}
	
	@Test
	public void givenAjsonString_WhenStringToDaoIsCalled_ThenGetObjectMapperShouldBeUsedOnce() {

		verify(safetynetalertFactory, times(1)).getObjectMapper();		
	
	}
	
	@Test
	public void givenAjsonString_WhenStringToDaoIsCalled_ThenJsonToStringShouldBeUsedOnce() {
				
		verify(sourceReader, times(1)).jsonToString();	
	
	}
	
	
}
