package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.PostPersonEndpointController;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.persons.SavePersonService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@WebMvcTest(controllers=PostPersonEndpointController.class)
public class PostPersonEndpointControllerTest {

	@Autowired
	private MockMvc postPersonEndpointControllerMockMvc;
	
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
		
		postPersonEndpointControllerMockMvc
			.perform(post("/person")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
					.accept(MediaType.APPLICATION_JSON))
			.andExpectAll(status().isCreated());
		
		verify(mockSavePersonService, times(1)).savePersons(any(Persons.class));
		
	}
	
	
}
