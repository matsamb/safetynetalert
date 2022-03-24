package com.safetynet.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.service.MedicalRecordsService;

@RestController
public class MedicalRecordsController {

	@Autowired
	MedicalRecordsService medicalRecordsServiceController;
	
	public MedicalRecordsController(MedicalRecordsService medicalRecordsServiceController) {
		this.medicalRecordsServiceController = medicalRecordsServiceController;
	}
	
	@GetMapping("/medicalrecords")
	public Iterable<MedicalRecords> getAllMedicalRecords(){
		return medicalRecordsServiceController.getAllMedicalRecords();
	}
	
}
