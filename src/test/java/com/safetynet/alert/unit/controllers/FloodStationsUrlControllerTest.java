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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.FloodStationsUrlController;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByStationService;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@WebMvcTest(controllers=FloodStationsUrlController.class)
public class FloodStationsUrlControllerTest {

	@Autowired
	private MockMvc floodStationsUrlControllerMockMvc;
	
	@MockBean
	private FindPersonByAddressService mockFindPersonByAddressService;
	
	@MockBean
	private FindMedicalRecordsByFirstNameAndLastNameService mockFindMedicalRecordsByFirstNameAndLastNameService;
	
	@MockBean
	private FindFirestationByStationService mockFindFirestationByStationService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@Test
	public void FloodStationsUrlTest() throws Exception{
		
		
		Persons p1 = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");
		Persons p3 = new Persons("Tenley","Boyd","1509 Culver St","Culver",97451,"841-874-6512","tenz@email.com");
		List<Persons> p = new ArrayList<>();
		p.add(p3);
		p.add(p1);
		
		Medications jbm1 = new Medications("aznol:350mg");
		Medications jbm2 = new Medications("hydrapermazol:100mg"); 
		Medications[] jbm = {jbm1, jbm2};
		
		Allergies jba1 = new Allergies("nillacilan");
		Allergies[] jba = {jba1};
		MedicalRecords m1 = new MedicalRecords("John","Boyd","03/06/1984",jbm,jba);
		
		Medications tbm1 = new Medications("N_A");
		Medications[] tbm = {tbm1};
		
		Allergies tba1 = new Allergies("peanuts");
		Allergies[] tba = {tba1};
		MedicalRecords m2 = new MedicalRecords("Tenley","Boyd","02/18/2012",tbm,tba);
		List<MedicalRecords> m = new ArrayList<>();
		m.add(m2);
		m.add(m1);
		
		Firestations f = new Firestations("1509 Culver St",3);
		List<Firestations> lf = new ArrayList<>();	
		lf.add(f);
		
		when(mockFindPersonByAddressService.findPersonByAddress(anyString())).thenReturn(p);
		when(mockFindMedicalRecordsByFirstNameAndLastNameService.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(m);
		when(mockFindFirestationByStationService.findByStation(anyInt())).thenReturn(lf);
		
		floodStationsUrlControllerMockMvc
			.perform(get("/flood/stations?stations=1,3"))
			.andExpectAll(status().isOk());
		
	}
	
	
}
