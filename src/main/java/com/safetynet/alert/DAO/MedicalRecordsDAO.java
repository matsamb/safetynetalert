package com.safetynet.alert.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.google.inject.internal.util.Objects;
import com.safetynet.alert.entity.MedicalRecords;

@Component
public class MedicalRecordsDAO {

	private static final Logger LOGGER= LogManager.getLogger("MedicalRecordsDAO");
	
	private static List<MedicalRecords> medicalRecords = new ArrayList<>();
	
	public List<MedicalRecords> findAllMedicalRecords(){
		return List.copyOf(MedicalRecordsDAO.medicalRecords);
	}
	
	public List<MedicalRecords> findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName){
		List<MedicalRecords> result = new ArrayList<>() ;
		for(MedicalRecords me: List.copyOf(MedicalRecordsDAO.medicalRecords)) {
			if (firstName.contains(me.getFirstName())  && lastName.contains(me.getLastName())) {
				System.out.println(me);
				result.add((MedicalRecords)me.clone());
			}
		}
		return result; 
	}
	
	public List<MedicalRecords> findMedicalRecordsByLastName(String lastName){
		List<MedicalRecords> result = new ArrayList<>() ;
		for(MedicalRecords p: List.copyOf(MedicalRecordsDAO.medicalRecords)) {
			if (lastName.contains(p.getLastName())) { 
				result.add((MedicalRecords)p.clone());
			}
		}
		return result; 
	}
	
	public void saveMedicalRecords(MedicalRecords medicalRecord) {
		MedicalRecordsDAO.medicalRecords.add((MedicalRecords)medicalRecord.clone());
	}
	
	public void saveAllMedicalRecords(List<MedicalRecords> medicalRecord) {
		medicalRecord.forEach(p -> saveMedicalRecords(p));
	}

	public void deleteMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		int i = 0;
		for(MedicalRecords me: List.copyOf(MedicalRecordsDAO.medicalRecords)) {
			if (firstName.contains(me.getFirstName())  && lastName.contains(me.getLastName())) { 			
				MedicalRecordsDAO.medicalRecords.remove(i);
				i--;
			}
			i++;
		}
	}
	
	public void deleteAllMedicalRecords() {
		MedicalRecordsDAO.medicalRecords.clear();
	}

}
