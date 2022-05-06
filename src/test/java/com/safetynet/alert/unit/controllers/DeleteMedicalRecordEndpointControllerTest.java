package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.DeleteMedicalRecordEndpointController;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.DeleteMedicalRecordsByFirstNameAndLastNameService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@WebMvcTest(controllers=DeleteMedicalRecordEndpointController.class)
public class DeleteMedicalRecordEndpointControllerTest {

	@Autowired
	private MockMvc deleteMedicalRecordEndpointControllerMockMvc;
	
	@MockBean
	private DeleteMedicalRecordsByFirstNameAndLastNameService mockDeleteMedicalRecordsByFirstNameAndLastNameService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@Test
	public void deleteMedicalRecordsByFirstNameAndLastName() throws Exception{
		
		deleteMedicalRecordEndpointControllerMockMvc
			.perform(delete("/medicalRecord?firstName=John&lastName=Boyd"))
			.andExpectAll(status().isOk());
		
		verify(mockDeleteMedicalRecordsByFirstNameAndLastNameService, times(1)).deleteByFirstNameAndLastName(anyString(), anyString());
		
	}
	
	
}
