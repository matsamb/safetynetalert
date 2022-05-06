package com.safetynet.alert.controller;

import java.net.URI;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.service.records.SaveMedicalRecordsService;

@RestController
public class PostMedicalRecordsEndpointController {

	static final Logger postMedicalRecordsEndpointControllerLogger = LogManager.getLogger("PostMedicalRecordsEndpointController");
	
	@Autowired
	SaveMedicalRecordsService saveMedicalRecordsServicePostMedicalRecordsEndpointController;
	
	PostMedicalRecordsEndpointController(SaveMedicalRecordsService saveMedicalRecordsServicePostMedicalRecordsEndpointController){
		this.saveMedicalRecordsServicePostMedicalRecordsEndpointController = saveMedicalRecordsServicePostMedicalRecordsEndpointController;
	}
	
	@PostMapping("medicalRecord")
	public ResponseEntity<MedicalRecords> addMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
		
		saveMedicalRecordsServicePostMedicalRecordsEndpointController.saveMedicalRecords(medicalRecords);
		MedicalRecords addedMedicalRecords = medicalRecords;
		
		if (Objects.isNull(addedMedicalRecords)) {

			postMedicalRecordsEndpointControllerLogger.error("the posted medical records request body is empty");
			return ResponseEntity.noContent().build();

		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
					.buildAndExpand(addedMedicalRecords.getFirstName(), addedMedicalRecords.getLastName()).toUri();
			postMedicalRecordsEndpointControllerLogger.debug("Posted medical records "+ addedMedicalRecords.getFirstName()+" "+addedMedicalRecords.getLastName()+" URI created");
			postMedicalRecordsEndpointControllerLogger.info(addedMedicalRecords.getFirstName()+" "+addedMedicalRecords.getLastName()+" Posted medical records created");
			return ResponseEntity.created(location).build();
		}

	}
	
}
