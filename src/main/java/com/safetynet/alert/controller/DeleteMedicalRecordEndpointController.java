package com.safetynet.alert.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.service.records.DeleteMedicalRecordsByFirstNameAndLastNameService;

@RestController
public class DeleteMedicalRecordEndpointController {

	static final Logger deleteMedicalRecordEndpointControllerLogger = LogManager.getLogger("DeleteMedicalRecordEndpointController");
	
	@Autowired
	DeleteMedicalRecordsByFirstNameAndLastNameService deleteMedicalRecordsByFirstNameAndLastNameServiceDeleteMedicalRecordEndpointController;
	
	DeleteMedicalRecordEndpointController(DeleteMedicalRecordsByFirstNameAndLastNameService deleteMedicalRecordsByFirstNameAndLastNameServiceDeleteMedicalRecordEndpointController){
		this.deleteMedicalRecordsByFirstNameAndLastNameServiceDeleteMedicalRecordEndpointController = deleteMedicalRecordsByFirstNameAndLastNameServiceDeleteMedicalRecordEndpointController;
	}
	
	@DeleteMapping("medicalRecord")//?firstName=<firstName>&lastName=<lastName>
	public void deleteMedicalRecords(@RequestParam String firstName, @RequestParam String lastName) {
		deleteMedicalRecordsByFirstNameAndLastNameServiceDeleteMedicalRecordEndpointController.deleteByFirstNameAndLastName(firstName, lastName);
		deleteMedicalRecordEndpointControllerLogger.info(firstName+" "+lastName+"'s medical records deleted");
	}
	
	
}
