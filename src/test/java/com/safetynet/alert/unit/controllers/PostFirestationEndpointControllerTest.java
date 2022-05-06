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

import com.safetynet.alert.controller.PostFirestationEndpointController;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.firestations.SaveFirestationService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@WebMvcTest(controllers=PostFirestationEndpointController.class)
public class PostFirestationEndpointControllerTest {

	@Autowired
	private MockMvc postFirestationEndpointControllerMockMvc;
	
	@MockBean
	private SaveFirestationService mockSaveFirestationService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@Test
	public void postNewFirestationTest() throws Exception{
				
		postFirestationEndpointControllerMockMvc
			.perform(post("/firestation")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"address\":\"11 Max St\",\"station\":5}")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
		
		verify(mockSaveFirestationService, times(1)).saveFirestation(any(Firestations.class));
	}
	
}
