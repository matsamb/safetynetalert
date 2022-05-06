package com.safetynet.alert.service.records;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.MedicalRecordsDAO;
import com.safetynet.alert.entity.MedicalRecords;

@Service
public class SaveMedicalRecordsService {

	static final Logger SaveMedicalRecordsServiceLogger = LogManager.getLogger("SaveMedicalRecordsService");
	
	@Autowired
	MedicalRecordsDAO medicalRecordsDAOSaveMedicalRecordsService;
	
	SaveMedicalRecordsService(MedicalRecordsDAO medicalRecordsDAOSaveMedicalRecordsService){
		this.medicalRecordsDAOSaveMedicalRecordsService = medicalRecordsDAOSaveMedicalRecordsService;
	}
	
	public void saveMedicalRecords(MedicalRecords medicalRecords) {
		SaveMedicalRecordsServiceLogger.debug(medicalRecords.getFirstName()+" "+medicalRecords.getLastName()+" medical records saved");
		medicalRecordsDAOSaveMedicalRecordsService.saveMedicalRecords(medicalRecords);
	}
	
}
