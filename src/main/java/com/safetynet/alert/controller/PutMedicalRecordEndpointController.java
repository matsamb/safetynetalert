package com.safetynet.alert.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.exceptions.SafetynetalertNotFoundException;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;
import com.safetynet.alert.service.records.SaveMedicalRecordsService;

@RestController
public class PutMedicalRecordEndpointController {

	static final Logger putMedicalRecordEndpointControllerLogger = LogManager.getLogger("PutMedicalRecordEndpointController");
	
	@Autowired
	SaveMedicalRecordsService saveMedicalRecordsServicePutMedicalRecordEndpointController;
	
	@Autowired
	FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServicePutMedicalRecordEndpointController;
	
	PutMedicalRecordEndpointController(SaveMedicalRecordsService saveMedicalRecordsServicePutMedicalRecordEndpointController
									, FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServicePutMedicalRecordEndpointController){
		this.findMedicalRecordsByFirstNameAndLastNameServicePutMedicalRecordEndpointController = findMedicalRecordsByFirstNameAndLastNameServicePutMedicalRecordEndpointController;
		this.saveMedicalRecordsServicePutMedicalRecordEndpointController = saveMedicalRecordsServicePutMedicalRecordEndpointController;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("medicalRecord")//?firstName=<firstName>&lastName=<lastName>
	public ResponseEntity<MedicalRecords> updateMedicalRecord(@RequestParam String firstName,
			@RequestParam String lastName, @RequestBody MedicalRecords newMedicalRecordsDetails) {

		MedicalRecords existingMedicalRecords = new MedicalRecords();
		for(MedicalRecords m:findMedicalRecordsByFirstNameAndLastNameServicePutMedicalRecordEndpointController.findByFirstNameAndLastName(firstName,lastName)) {
			existingMedicalRecords = m;
		};
		
		if (Objects.isNull(newMedicalRecordsDetails)) {
			throw new SafetynetalertNotFoundException(firstName + " " + lastName + " medical records not registered");
		} else {

			putMedicalRecordEndpointControllerLogger.info(firstName + " " + lastName + "'s medical records found");
			
			existingMedicalRecords.setAllergies(newMedicalRecordsDetails.getAllergies());
			existingMedicalRecords.setBirthDate(newMedicalRecordsDetails.getBirthDate());
			existingMedicalRecords.setMedications(newMedicalRecordsDetails.getMedications());

			saveMedicalRecordsServicePutMedicalRecordEndpointController.saveMedicalRecords(existingMedicalRecords);
			MedicalRecords updatedMedicalRecords = existingMedicalRecords;
			putMedicalRecordEndpointControllerLogger.info(firstName + " " + lastName + "'s medical updated");
			
			return ResponseEntity.ok(updatedMedicalRecords);
		}

	}
	
}
