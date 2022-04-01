package com.safetynet.alert.controller;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alert.entity.ChildAlert;
import com.safetynet.alert.entity.CommunityEmail;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.PersonInfo;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.entity.PhoneAlert;
import com.safetynet.alert.entity.StationNumber;
import com.safetynet.alert.repository.ChildAlertRepository;
import com.safetynet.alert.repository.CommunityEmailRepository;
import com.safetynet.alert.repository.FirestationsRepository;
import com.safetynet.alert.repository.MedicalRecordsRepository;
import com.safetynet.alert.repository.PersonInfoRepository;
import com.safetynet.alert.repository.PersonsRepository;
import com.safetynet.alert.repository.PhoneAlertRepository;
import com.safetynet.alert.repository.StationNumberRepository;
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
	StationNumberRepository stationNumberControllerRepository;
	
	@Autowired
	ChildAlertRepository childAlertControllerRepository;
	
	@Autowired
	PhoneAlertRepository phoneAlertControllerRepository;
	
	@Autowired
	CommunityEmailRepository communityEmailControllerRepository;
	
	@Autowired
	PersonInfoRepository personInfoControllerRepository;
	
	@Autowired
	private SafetynetService safetynetServiceController;
	
	public SafetynetalertController(SafetynetService safetynetServiceController
			,PersonsRepository personsControllerRepository
			,MedicalRecordsRepository medicalRecordsControllerRepository
			,FirestationsRepository firestationsControllerRepository
			,StationNumberRepository stationNumberControllerRepository
			,PhoneAlertRepository phoneAlertControllerRepository
			,CommunityEmailRepository communityEmailControllerRepository
			,PersonInfoRepository personInfoControllerRepository
			) {
		this.safetynetServiceController = safetynetServiceController;
		this.personsControllerRepository = personsControllerRepository;
		this.medicalRecordsControllerRepository = medicalRecordsControllerRepository;
		this.firestationsControllerRepository = firestationsControllerRepository;
		this.stationNumberControllerRepository = stationNumberControllerRepository;
		this.phoneAlertControllerRepository = phoneAlertControllerRepository;
		this.communityEmailControllerRepository = communityEmailControllerRepository;
		this.personInfoControllerRepository = personInfoControllerRepository;
	}
//URL	
	//manque decompte adult et enfant
	@GetMapping("firestation/stationNumber={stationNumber}")
	public Iterable<StationNumber> getStationNumber(@PathVariable int stationNumber){
		return stationNumberControllerRepository.getCustomStationNumberUrl(stationNumber) ;
	}
	
	@GetMapping("/phoneAlert/firestation={station}")
	public Iterable<PhoneAlert> getPhoneAlert(@PathVariable int station){
		return phoneAlertControllerRepository.getCustomPhoneAlertUrl(station);
	}
	
	@GetMapping("/childAlert/address={address}")
	public Iterable<ChildAlert> getChildAlert(@PathVariable String address){
		return childAlertControllerRepository.getCustomChildAlertUrl(address);
	}

	@GetMapping("/communityEmail/city={city}")//email de tous les habitants
	public Iterable<CommunityEmail> getCommunityEmail(@PathVariable String city){
		return communityEmailControllerRepository.getCustomCommunityEmailUrl(city);
	}
	
	@RequestMapping(path = "/personInfo/{firstName}/{lastName}", method = RequestMethod.GET)
	public Iterable<PersonInfo> getPersonInfo(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName){
		return personInfoControllerRepository.getPersonInfoUrl(firstName, lastName);
	}
	
//post mapping
	
	@PostMapping("/person")
	public ResponseEntity<Persons> addPersons(@RequestBody Persons persons) {
		Persons addedProduct = personsControllerRepository.save(persons);

		if (Objects.isNull(addedProduct)) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{firstName}/{lastName}")
				.buildAndExpand(addedProduct.getFirstName(),addedProduct.getLastName())
				.toUri();
		return ResponseEntity.created(location).build();
	
	}
	
	//@GetMapping("/flood/stations{stations}")

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
	//test	
		@GetMapping("/persons")
		public Iterable<Persons> getAllPersons(){
			return safetynetServiceController.getAllPersons() ;
		}
		
		@GetMapping("/medicalrecords")
		public Iterable<MedicalRecords> getAllMedicalRecords(){
			return safetynetServiceController.getAllMedicalRecords() ;
		}
		
		@GetMapping("/firestations")
		public Iterable<Firestations> getAllFirestations(){
			return safetynetServiceController.getAllFirestations() ;
		}
		
}
