package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.FirestationUrlController;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByStationService;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationUrlControllerTest {

	@Autowired
	private MockMvc firestationUrlControllerMockMvc;
	
	@MockBean
	private FindPersonByAddressService mockFindPersonByAddressService;
	
	@MockBean
	private FindFirestationByStationService mockFindFirestationByStationService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@Test
	public void findFirestation3Test() throws Exception{
		
		Firestations f = new Firestations("1509 Culver St",3);
		List<Firestations> lf = new ArrayList<>();
		lf.add(f);
		
		Persons p1 = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");
		Persons p3 = new Persons("Tenley","Boyd","1509 Culver St","Culver",97451,"841-874-6512","tenz@email.com");
	
		List<Persons> expectedPersons = new ArrayList<Persons>();
		expectedPersons.add(p1);
		expectedPersons.add(p3);
		
		when(mockFindFirestationByStationService.findByStation(anyInt())).thenReturn(lf);
		when(mockFindPersonByAddressService.findPersonByAddress(anyString())).thenReturn(expectedPersons);
		
		firestationUrlControllerMockMvc
			.perform(get("/firestation?station=3"))
			.andExpectAll(status().isOk());
		
	}
	
	
	
}
