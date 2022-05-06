package com.safetynet.alert.service.records;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.MedicalRecordsDAO;
import com.safetynet.alert.entity.MedicalRecords;

@Service
public class FindMedicalRecordsByFirstNameAndLastNameService {

	static final Logger findMedicalRecordsByFirstNameAndLastNameServiceLogger = LogManager.getLogger("FindMedicalRecordsByFirstNameAndLastNameService");
	
	@Autowired
	MedicalRecordsDAO medicalRecordsDAOFindMedicalRecordsByFirstNameAndLastNameService;
	
	FindMedicalRecordsByFirstNameAndLastNameService(MedicalRecordsDAO medicalRecordsDAOFindMedicalRecordsByFirstNameAndLastNameService){
		this.medicalRecordsDAOFindMedicalRecordsByFirstNameAndLastNameService = medicalRecordsDAOFindMedicalRecordsByFirstNameAndLastNameService;
	}
	
	public List<MedicalRecords> findByFirstNameAndLastName(String firstName, String lastName) {
		findMedicalRecordsByFirstNameAndLastNameServiceLogger.debug("searching "+firstName+" "+lastName+" medical records");
		return medicalRecordsDAOFindMedicalRecordsByFirstNameAndLastNameService.findMedicalRecordsByFirstNameAndLastName(firstName, lastName);
	}
}
