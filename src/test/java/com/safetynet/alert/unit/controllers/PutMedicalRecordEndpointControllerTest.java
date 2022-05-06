package com.safetynet.alert.unit.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.PutMedicalRecordEndpointController;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;
import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;
import com.safetynet.alert.service.records.SaveMedicalRecordsService;

@WebMvcTest(controllers=PutMedicalRecordEndpointController.class)
public class PutMedicalRecordEndpointControllerTest {

	@Autowired
	private MockMvc putMedicalRecordEndpointControllerMockMvc;
	
	@MockBean
	private SaveMedicalRecordsService mockSaveMedicalRecordsService;
	
	@MockBean
	private FindMedicalRecordsByFirstNameAndLastNameService mockFindMedicalRecordsByFirstNameAndLastNameService;
	
	@MockBean
	private PersonsDaoLoaderService mockPersonsDaoLoaderService;
	
	@MockBean
	private FirestationsDaoLoaderService mockFirestationsDaoLoaderService;
	
	@MockBean
	private MedicalRecordsDaoLoaderService mockMedicalRecordsDaoLoaderService;
	
	@Test
	public void putMedicalRecordsTest() throws Exception{
		
		Medications tbm1 = new Medications("N_A");
		Medications[] tbm = {tbm1};
		
		Allergies tba1 = new Allergies("peanuts");
		Allergies[] tba = {tba1};
		MedicalRecords m2 = new MedicalRecords("Tenley","Boyd","02/18/2012",tbm,tba);
		
		List<MedicalRecords> l = new ArrayList<>();
		l.add(m2);
		
		when(mockFindMedicalRecordsByFirstNameAndLastNameService
				.findByFirstNameAndLastName("Tenley", "Boyd"))
			.thenReturn(l);
		
		putMedicalRecordEndpointControllerMockMvc
			.perform(put("/medicalRecord?firstName=Tenley&lastName=Boyd")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"birthDate\":\"02/18/2012\", \"medications\":[\"N_A\"], \"allergies\":[\"peanut\"]}")
					.accept(MediaType.APPLICATION_JSON))
			.andExpectAll(status().isOk());
		
		verify(mockSaveMedicalRecordsService, times(1)).saveMedicalRecords(any(MedicalRecords.class));
		
	}
	
}
