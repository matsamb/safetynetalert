package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.PutPersonEndpointController;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.FindPersonByFirstNameAndLastNameService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.persons.SavePersonService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@SpringBootTest
@AutoConfigureMockMvc
public class PutPersonEndpointControllerTest {

	@Autowired
	private MockMvc putPersonEndpointControllerMockMvc;
	
	@MockBean
	private FindPersonByFirstNameAndLastNameService mockFindPersonByFirstNameAndLastNameService;
	
	@MockBean
	private SavePersonService mockSavePersonService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@Test
	public void postPersonTest() throws Exception{
		
		Persons jb = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");
		
		when(mockFindPersonByFirstNameAndLastNameService.findPersonsByFirstNameAndLastName("John", "Boyd")).thenReturn(jb);
		
		putPersonEndpointControllerMockMvc
			.perform(put("/person?firstName=John&lastName=Boyd")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
					.accept(MediaType.APPLICATION_JSON))
			.andExpectAll(status().isOk());
		
		verify(mockSavePersonService, times(1)).savePersons(any(Persons.class));
		
	}
	
}
