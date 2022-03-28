package com.safetynet.alert.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.repository.FirestationsRepository;
import com.safetynet.alert.repository.MedicalRecordsRepository;
import com.safetynet.alert.repository.PersonsRepository;
import com.safetynet.alert.service.SafetynetService;

@RestController
public class SafetynetalertController {

	@Autowired
	PersonsRepository personsControllerRepository;
	
	@Autowired
	MedicalRecordsRepository medicalRecordsControllerRepository;
	
	@Autowired
	FirestationsRepository firestationsControllerRepository;
	
	@Autowired
	private SafetynetService safetynetServiceController;
	
	public SafetynetalertController(SafetynetService safetynetServiceController
			,PersonsRepository personsControllerRepository
			,MedicalRecordsRepository medicalRecordsControllerRepository
			,FirestationsRepository firestationsControllerRepository
			) {
		this.safetynetServiceController = safetynetServiceController;
		this.personsControllerRepository = personsControllerRepository;
		this.medicalRecordsControllerRepository = medicalRecordsControllerRepository;
		this.firestationsControllerRepository = firestationsControllerRepository;
	}
	
	@GetMapping("/persons")/*{stationNumber*///}
	public Iterable<Persons> getAllPersons(){
		return safetynetServiceController.getAllPersons() ;
	}
	
	@GetMapping("/medicalrecords")/*{stationNumber*///}
	public Iterable<MedicalRecords> getAllMedicalRecords(){
		return safetynetServiceController.getAllMedicalRecords() ;
	}
	
	@GetMapping("/firestations")/*{stationNumber*///}
	public Iterable<Firestations> getAllFirestations(){
		return safetynetServiceController.getAllFirestations() ;
	}
	/*
	@GetMapping("/childAlert{address}")
	public Iterable
	*/
/*	
	@GetMapping("/phoneAlert{station}")	
	EntityManager entityManager = 
	Query q = entityManager.createNativeQuery("select persons.phone, persons.first_name, persons.last_name from persons, firestations where firestations.station=? and persons.address = firestations.address order by last_name asc ;");
	List<Object[]> empObject= q.getResultList();
	query
	"select persons.phone, persons.first_name, persons.last_name from persons, firestations where firestations.station=? and persons.address = firestations.address order by last_name asc ;"
*/
	/*
	@GetMapping("/fire{address}")
	
	ajouter temps et age
	"select  distinct persons.first_name, persons.last_name, persons.phone
	, firestations.station, medical_records_allergies.allergy
	, medical_records_medications.medication , if( DATE_ADD(STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'),INTERVAL 18 year) > curdate()) from persons, 
	firestations, medical_records_allergies,medical_records_medications 
		where persons.address = ? and 
			firestations.address = persons.address and 
			(medical_records_allergies.medical_records_first_name = persons.first_name and 
			medical_records_allergies.medical_records_last_name = persons.last_name and 
			medical_records_medications.medical_records_first_name = persons.first_name and 
			medical_records_medications.medical_records_last_name = persons.last_name) 
			order by persons.last_name
	;"
	
	
	@GetMapping("/flood/stations{stations}")//
	
	@GetMapping("/personInfo{firstName&LastName")//persons + medicalrecords
	
	@GetMapping("/communityEmail{city}")//email de tous les habitants
	
	
	@PostMapping("person")
	@PutMapping("person")
	@PatchMapping("person")
	@DeleteMapping("person")

	@PostMapping("firestation")
	@PutMapping("firestation")
	@PatchMapping("firestation")
	@DeleteMapping("firestation")
	
	@PostMapping("medicalRecord")
	@PutMapping("medicalRecord")
	@PatchMapping("medicalRecord")
	@DeleteMapping("medicalRecord")

	*/
}
