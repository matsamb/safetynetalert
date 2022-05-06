package com.safetynet.alert.unit.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.DAO.MedicalRecordsDAO;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsDaoTest {
	
	@InjectMocks
	private MedicalRecordsDAO medicalRecordsDao;

	@AfterEach
	public void clean() {
		medicalRecordsDao.deleteAllMedicalRecords();
	}
	
	@Test
	public void givenAEmptyList_whenSavingAMedicalRecordsThenSizeShouldBe1() {

		Medications tbm1 = new Medications("N_A");
		Medications[] tbm = {tbm1};
		
		Allergies tba1 = new Allergies("peanuts");
		Allergies[] tba = {tba1};
		MedicalRecords m2 = new MedicalRecords("Tenley","Boyd","02/18/2012",tbm,tba);
		
		medicalRecordsDao.saveMedicalRecords(m2);
		
		assertThat(medicalRecordsDao.findAllMedicalRecords()).hasSize(1);
		
	}
	
	@Test
	public void givenAEmptyList_whenSavingAListOf2MedicalRecordsThenSizeShouldBe2() {
		
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
		
		List<MedicalRecords> lm = new ArrayList<MedicalRecords>();
		lm.add(m2);
		lm.add(m1);
		
		medicalRecordsDao.saveAllMedicalRecords(lm);
		
		assertThat(medicalRecordsDao.findAllMedicalRecords()).hasSize(2);
		
	}	
	
	@Nested
	class CommonSetting{
	@BeforeEach
	public void init() {
		
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
		
		medicalRecordsDao.saveMedicalRecords(m1);
		medicalRecordsDao.saveMedicalRecords(m2);
			
	}
	
	@Test
	public void givenASize2List_whenDeletingAMedicalRecordsThenSizeShouldBe1() {
		
		medicalRecordsDao.deleteMedicalRecordsByFirstNameAndLastName("Tenley", "Boyd");
				
		assertThat(medicalRecordsDao.findAllMedicalRecords()).hasSize(0);
		
	}
	
	@Test
	public void findAllMedicalRecords_ShouldReturnAListOfSize2() {
		
		assertThat(medicalRecordsDao.findAllMedicalRecords())
			.isInstanceOf(List.class)
			.isNotEmpty()
			.hasSize(2);
		
	}
	
	@Test
	public void findMedicalRecordsByFirstNameAndLastName_ShouldReturnFirstNameAndLastNameMedicalRecords() {
		
		MedicalRecords result = medicalRecordsDao.findMedicalRecordsByFirstNameAndLastName("John", "Boyd").get(0);
		
		assertThat(result.getFirstName()).contains("John");
		assertThat(result.getLastName()).isEqualTo("Boyd");
		
	}
	
	@Test
	public void findMedicalRecordsByLastName_ShouldHaveSize2() {
		
		List<MedicalRecords> result = medicalRecordsDao.findMedicalRecordsByLastName("Boyd");
		
		assertThat(result).hasSize(2);
		
	}
}
	
	
	
	
}
