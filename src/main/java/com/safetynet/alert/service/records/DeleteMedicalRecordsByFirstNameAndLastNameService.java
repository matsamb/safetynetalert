package com.safetynet.alert.service.records;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.MedicalRecordsDAO;

@Service
public class DeleteMedicalRecordsByFirstNameAndLastNameService {

	static final Logger deleteMedicalRecordsByFirstNameAndLastNameServiceLogger = LogManager.getLogger("DeleteMedicalRecordsByFirstNameAndLastNameService");
	
	@Autowired
	MedicalRecordsDAO medicalRecordsDAODeleteMedicalRecordsByFirstNameAndLastNameService;
	
	DeleteMedicalRecordsByFirstNameAndLastNameService(MedicalRecordsDAO medicalRecordsDAODeleteMedicalRecordsByFirstNameAndLastNameService){
		this.medicalRecordsDAODeleteMedicalRecordsByFirstNameAndLastNameService = medicalRecordsDAODeleteMedicalRecordsByFirstNameAndLastNameService;
	}
	
	public void deleteByFirstNameAndLastName(String firstName, String lastName) {
		deleteMedicalRecordsByFirstNameAndLastNameServiceLogger.debug("droping "+firstName+" "+lastName+" medical records");
		medicalRecordsDAODeleteMedicalRecordsByFirstNameAndLastNameService.deleteMedicalRecordsByFirstNameAndLastName(firstName, lastName);
	}
	
}
