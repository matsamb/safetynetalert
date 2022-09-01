package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.PostMedicalRecordsEndpointController;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;
import com.safetynet.alert.service.records.SaveMedicalRecordsService;

@SpringBootTest
@AutoConfigureMockMvc
public class PostMedicalRecordsEndpointControllerTest {

	@Autowired
	private MockMvc postMedicalRecordsEndpointControllerMockMvc;
	
	@MockBean
	private SaveMedicalRecordsService mockSaveMedicalRecordsService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@Test
	public void postMedicalRecordsTest() throws Exception{
		
		postMedicalRecordsEndpointControllerMockMvc
			.perform(post("/medicalRecord")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"birthDate\":\"02/18/2012\", \"medications\":[\"N_A\"], \"allergies\":[\"peanut\"]}")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
		
		verify(mockSaveMedicalRecordsService, times(1)).saveMedicalRecords(any(MedicalRecords.class));
		
	}
	
	
}
