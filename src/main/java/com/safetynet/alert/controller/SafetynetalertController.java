package com.safetynet.alert.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.safetynet.alert.entity.ChildAlert;
import com.safetynet.alert.entity.CommunityEmail;
import com.safetynet.alert.entity.FirePlaces;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.FloodStations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.PersonsInfo;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.entity.PhoneAlert;
import com.safetynet.alert.entity.StationNumber;
import com.safetynet.alert.exceptions.SafetynetalertNotFoundException;
import com.safetynet.alert.service.SafetynetService;

@RestController
@Transactional
public class SafetynetalertController {

	@Autowired
	private SafetynetService safetynetServiceController;

	public SafetynetalertController(SafetynetService safetynetServiceController){
		this.safetynetServiceController = safetynetServiceController;
	}

//URL	
	// manque decompte adult et enfant
	@GetMapping("firestation/{stationNumber}")
	public Iterable<StationNumber> getStationNumber(@PathVariable int stationNumber) {
		return safetynetServiceController.getCustomStationNumber(stationNumber);
	}
	
	@GetMapping("/phoneAlert/{station}")
	public Iterable<PhoneAlert> getPhoneAlert(@PathVariable int station) {
		return safetynetServiceController.getCustomPhoneAlert(station);
	}
	
	@GetMapping("/childAlert/address={address}")
	public Iterable<ChildAlert> getChildAlert(@PathVariable String address) {
		return safetynetServiceController.getCustomChildAlert(address);
	}
	
	@GetMapping("/communityEmail/{city}") // email de tous les habitants
	public Iterable<CommunityEmail> getCommunityEmail(@PathVariable String city) {
		return safetynetServiceController.getCustomCommunityEmail(city);
	}
	
	@GetMapping("/fire/{address}")
	public Iterable<FirePlaces> getFirePlaces(@PathVariable String address){
		return safetynetServiceController.getFirePlaces(address);
	}
	
	@GetMapping("/personsInfo/{firstName}&{lastName}")
	public Iterable<PersonsInfo> getPersonsInfo(@PathVariable(value = "firstName") String firstName,
			@PathVariable(value = "lastName") String lastName) {
		return safetynetServiceController.getPersonsInfo(firstName, lastName);
	}
	
	@GetMapping("/flood/{stations}")
	public Iterable<FloodStations> getFloodStations(@PathVariable String... stations){
		List<String> stationsTable = Arrays.asList(stations);//.stream().collect(Collectors.toList());
		return safetynetServiceController.getFloodStations(stationsTable);
	}
		  
//persons modifier access

	@PostMapping("/person/new")
	public ResponseEntity<Persons> addPersons(@RequestBody Persons persons) {
		Persons addedPerson = safetynetServiceController.savePersons(persons);
		
		if (Objects.isNull(addedPerson)) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
				.buildAndExpand(addedPerson.getFirstName(), addedPerson.getLastName()).toUri();
		return ResponseEntity.created(location).build();

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("person/update/{firstName}/{lastName}")
	public ResponseEntity<Persons> upDatePersons(@PathVariable String firstName, @PathVariable String lastName,
			@RequestBody Persons newPersonDetails) {

		Persons existingPerson = safetynetServiceController.findPersonsByFirstNameAndLastName(firstName, lastName);
		
		if (Objects.isNull(existingPerson)) {

			throw new SafetynetalertNotFoundException("Person " + firstName + " " + lastName + " is not registered");

		} else {

			existingPerson.setAddress(newPersonDetails.getAddress());
			existingPerson.setCity(newPersonDetails.getCity());
			existingPerson.setZip(newPersonDetails.getZip());
			existingPerson.setPhone(newPersonDetails.getPhone());
			existingPerson.setEmail(newPersonDetails.getEmail());

			final Persons updatePerson = safetynetServiceController.savePersons(existingPerson);
			return ResponseEntity.ok(updatePerson);
		}
	}

	@DeleteMapping("person/delete/{firstName}/{lastName}")
	public void deleteOnePerson(@PathVariable String firstName, @PathVariable String lastName) {
		safetynetServiceController.deletePersonsByFirstNameAndLastName(firstName, lastName);
	}

//firestations modifier access

	@PostMapping("firestation/new")
	public ResponseEntity<Firestations> addFirestations(@RequestBody Firestations firestation) {
		Firestations addedFirestation = safetynetServiceController.saveFirestations(firestation);
		
		if (Objects.isNull(addedFirestation)) {

			return ResponseEntity.noContent().build();
		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{stationAddress}")
					.buildAndExpand(addedFirestation.getAddress()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("firestation/update/{stationNumber}")
	public ResponseEntity<Firestations> updateFirestation(@PathVariable int stationNumber, @RequestBody Firestations newFirestationDetails) {

		
		Firestations existingFirestation = safetynetServiceController.getFirestationsByStation(stationNumber);
		
		if(Objects.isNull(existingFirestation)) {
			throw new SafetynetalertNotFoundException("Firestation number "+stationNumber+", does not exist.");
		}else {
			
			existingFirestation.setStation(newFirestationDetails.getStation());
			existingFirestation.setAddress(newFirestationDetails.getAddress());
			
			final Firestations updatedFirestation = safetynetServiceController.saveFirestations(existingFirestation);
			return ResponseEntity.ok(updatedFirestation);
	
		}
	}

	@DeleteMapping("firestation/delete/{address}")
	public void deleteFirestation(@PathVariable String address) {
		safetynetServiceController.deleteFirestationsByAddress(address);
	}

//medicalRecords modifier access

	@PostMapping("medicalRecord")
	public ResponseEntity<MedicalRecords> addMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
		MedicalRecords addedMedicalRecords = safetynetServiceController.saveMedicalRecords(medicalRecords);
		
		if (Objects.isNull(addedMedicalRecords)) {

			return ResponseEntity.noContent().build();

		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
					.buildAndExpand(addedMedicalRecords.getFirstName(), addedMedicalRecords.getLastName()).toUri();
			return ResponseEntity.created(location).build();
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("medicalRecord/{firstName}/{lastName}")
	public ResponseEntity<MedicalRecords> updateMedicalRecord(@PathVariable String firstName,
			@PathVariable String lastName, @RequestBody MedicalRecords newMedicalRecordsDetails) {

		MedicalRecords existingMedicalRecords = safetynetServiceController.getMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);
		
		if (Objects.isNull(newMedicalRecordsDetails)) {
			throw new SafetynetalertNotFoundException(firstName + " " + lastName + " medical records not registered");
		} else {

			existingMedicalRecords.setAllergies(newMedicalRecordsDetails.getAllergies());
			existingMedicalRecords.setBirthDate(newMedicalRecordsDetails.getBirthDate());
			existingMedicalRecords.setMedications(newMedicalRecordsDetails.getMedications());

			MedicalRecords updatedMedicalRecords = safetynetServiceController.saveMedicalRecords(existingMedicalRecords);
			return ResponseEntity.ok(updatedMedicalRecords);
		}

	}

	@DeleteMapping("medicalRecord/{firstName}/{lastName}")
	public void deleteMedicalRecords(@PathVariable String firstName, @PathVariable String lastName) {
		safetynetServiceController.deleteMedicalRecordsByFirstNameAndLastName(firstName, lastName);
	}

//test read

	@GetMapping("/persons")
	public Iterable<Persons> getAllPersons() {
		return safetynetServiceController.getAllPersons();
	}

	@GetMapping("/medicalrecords")
	public Iterable<MedicalRecords> getAllMedicalRecords() {
		return safetynetServiceController.getAllMedicalRecords();
	}

	@GetMapping("/firestations")
	public Iterable<Firestations> getAllFirestations() {
		return safetynetServiceController.getAllFirestations();
	}

}
