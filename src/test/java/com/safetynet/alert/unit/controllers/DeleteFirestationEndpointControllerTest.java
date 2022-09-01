package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.DeleteFirestationEndpointController;
import com.safetynet.alert.service.firestations.DeleteFirestationByAddressService;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteFirestationEndpointControllerTest {

	@Autowired
	private MockMvc deleteFirestationEndpointControllerMockMvc;
	
	@MockBean
	private DeleteFirestationByAddressService mockDeleteFirestationByAddressService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@Test
	public void deleteFirestationByAddress1509CulverSt() throws Exception {
		
		deleteFirestationEndpointControllerMockMvc
			.perform(delete("/firestation?address=1509 Culver St"))
			.andExpectAll(status().isOk());
		
		verify(mockDeleteFirestationByAddressService,times(1)).deleteByAddress(anyString());
		
	}
	
}
