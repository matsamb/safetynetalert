package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.anyString;
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

import com.safetynet.alert.controller.PutFirestationEndpointController;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.service.firestations.FindFirestationByAddressService;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.firestations.SaveFirestationService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@SpringBootTest
@AutoConfigureMockMvc
public class PutFirestationEndpointControllerTest {

	@Autowired
	private MockMvc putFirestationEndpointControllerMockMvc;
	
	@MockBean
	private SaveFirestationService mockSaveFirestationService;
	
	@MockBean
	private FindFirestationByAddressService mockFindFirestationByAddressService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@Test
	public void putFirestationTest() throws Exception{
		
		Firestations f = new Firestations("11 Max St",5);
		
		when(mockFindFirestationByAddressService.findByAddress(anyString())).thenReturn(f);
		
		putFirestationEndpointControllerMockMvc
			.perform(put("/firestation?address=11 Max St")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"address\":\"11 Max St\",\"station\":5}")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		verify(mockSaveFirestationService, times(1)).saveFirestation(f);
		
	}

}
