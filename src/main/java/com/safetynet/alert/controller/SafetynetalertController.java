package com.safetynet.alert.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.safetynet.alert.entity.ChildAlertAgregV2;
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

	private static Logger controllerLogger = LogManager.getLogger("SafetynetController");
	
	@Autowired
	SafetynetService safetynetServiceController;

	public SafetynetalertController(SafetynetService safetynetServiceController){
		this.safetynetServiceController = safetynetServiceController;
	}

	@GetMapping("/home")
	public @ResponseBody String greeting() {
		return "WELCOME TO SAFETY NET' ALERT.";
	}
	
//URL	
	@GetMapping("firestation")//?stationNumber=<station>
	public Iterable<StationNumber> getStationNumber(@RequestParam int stationNumber) {
		controllerLogger.info("URI firestation/"+stationNumber+", displayed");
		return safetynetServiceController.getCustomStationNumber(stationNumber);
	}
	
	@GetMapping("phoneAlert")//?station=<station>
	public Iterable<PhoneAlert> getPhoneAlert(@RequestParam int station) {
		controllerLogger.info("URI phoneAlert/"+station+", displayed");
		return safetynetServiceController.getCustomPhoneAlert(station);
	}

//version2	
	@GetMapping("childAlert")//?address=<address>
	public ChildAlertAgregV2 getChildAlert(@RequestParam String address) {
		
		ChildAlertAgregV2 iChildAlertAgregsList = new ChildAlertAgregV2();
		
		if(safetynetServiceController.getCustomChildAlert(address)!= null) {
			iChildAlertAgregsList.setChild(safetynetServiceController.getCustomChildAlert(address));
			iChildAlertAgregsList.setAdult(safetynetServiceController.getCustomChildAlertAdult(address));
					
		}
		
		controllerLogger.info("URI childAlert?address="+address+", displayed");
		return iChildAlertAgregsList;
	}
	
	
	
	
	@GetMapping("communityEmail")/*?city=<city>"*/ // email de tous les habitants
	public Iterable<CommunityEmail> getCommunityEmail(@RequestParam String city) {
		controllerLogger.info("URI communityEmail/"+city+", displayed");
		return safetynetServiceController.getCustomCommunityEmail(city);
	}
	
	@GetMapping("fire")//?address=<address>
	public Iterable<FirePlaces> getFirePlaces(@RequestParam String address){
		controllerLogger.info("URI fire/"+address+", displayed");
		return safetynetServiceController.getFirePlaces(address);
	}
	
	@GetMapping("personInfo")//?firstName=<firstName>&lastName=<lastName>
	public Iterable<PersonsInfo> getPersonsInfo(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {
		controllerLogger.info("URI personsInfo/"+firstName+"&"+lastName+", displayed");
		return safetynetServiceController.getPersonsInfo(firstName, lastName);
	}
		
	@GetMapping("flood/stations")//?stations=1,2,3,4
	public Iterable<FloodStations> getFloodStations(@RequestParam(value = "stations") List<Integer> stations){
		controllerLogger.info("URI flood/"+stations+", displayed");
		List<FloodStations> iFloodStations = new ArrayList<FloodStations>();
		stations.forEach(station->{	
		//System.out.println(station);
		iFloodStations.addAll(safetynetServiceController.getFloodStations(station));
		//iFloodStations.forEach(System.out::println);
		});
		return iFloodStations;
	}
		  
//persons modifier access

	@GetMapping("person/{firstName}/{lastName}")
	public Persons findPerson(@PathVariable String firstName, @PathVariable String lastName) {
		
		return safetynetServiceController.findPersonsByFirstNameAndLastName(firstName, lastName);
	}
		
	@PostMapping("person")
	public ResponseEntity<Persons> addPersons(@RequestBody Persons persons) {
		Persons addedPerson = safetynetServiceController.savePersons(persons);
		controllerLogger.debug("Posted person recovered");
		
		if (Objects.isNull(addedPerson)) {
			controllerLogger.error("the posted person request body is empty");
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
				.buildAndExpand(addedPerson.getFirstName(), addedPerson.getLastName()).toUri();
		controllerLogger.debug("Posted person "+ addedPerson.getFirstName()+" "+addedPerson.getLastName()+" URI created");
		controllerLogger.info("The posted person "+ addedPerson.getFirstName()+" "+addedPerson.getLastName()+" created");
		return ResponseEntity.created(location).build();

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("person/{firstName}/{lastName}")
	public ResponseEntity<Persons> upDatePersons(@PathVariable String firstName, @PathVariable String lastName,
			@RequestBody Persons newPersonDetails) {

		Persons existingPerson = safetynetServiceController.findPersonsByFirstNameAndLastName(firstName, lastName);
		
		if (Objects.isNull(existingPerson)) {
			throw new SafetynetalertNotFoundException("Person " + firstName + " " + lastName + " is not registered");

		} else {

			controllerLogger.info("Person " + firstName + " " + lastName + " found");
			
			existingPerson.setAddress(newPersonDetails.getAddress());
			existingPerson.setCity(newPersonDetails.getCity());
			existingPerson.setZip(newPersonDetails.getZip());
			existingPerson.setPhone(newPersonDetails.getPhone());
			existingPerson.setEmail(newPersonDetails.getEmail());

			final Persons updatePerson = safetynetServiceController.savePersons(existingPerson);
			controllerLogger.info("Person " + firstName + " " + lastName + " updated");
			return ResponseEntity.ok(updatePerson);
		}
	}

	@DeleteMapping("person/{firstName}/{lastName}")
	public void deleteOnePerson(@PathVariable String firstName, @PathVariable String lastName) {
		safetynetServiceController.deletePersonsByFirstNameAndLastName(firstName, lastName);
		controllerLogger.info("Person "+ firstName+" "+lastName+" deleted");
	}

//firestations modifier access

	@PostMapping("firestation")
	public ResponseEntity<Firestations> addFirestations(@RequestBody Firestations firestation) {
		Firestations addedFirestation = safetynetServiceController.saveFirestations(firestation);
		
		if (Objects.isNull(addedFirestation)) {
			controllerLogger.error("the posted firestation request body is empty");
			return ResponseEntity.noContent().build();
		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{stationAddress}")
					.buildAndExpand(addedFirestation.getAddress()).toUri();
			controllerLogger.debug("Posted firestation "+addedFirestation.getAddress()+" URI created");
			controllerLogger.info("Posted firestation "+addedFirestation.getAddress()+" created");
			return ResponseEntity.created(location).build();
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@PutMapping("firestation/{stationNumber}")
	public ResponseEntity<Firestations> updateFirestation(@PathVariable int stationNumber, @RequestBody Firestations newFirestationDetails) {

		
		Firestations existingFirestation = safetynetServiceController.getFirestationsByStation(stationNumber);
		
		if(Objects.isNull(existingFirestation)) {
			throw new SafetynetalertNotFoundException("Firestation number "+stationNumber+", does not exist.");
		}else {
			
			controllerLogger.info("Firestation number "+stationNumber+ " found");
			
			existingFirestation.setStation(newFirestationDetails.getStation());
			existingFirestation.setAddress(newFirestationDetails.getAddress());
			
			final Firestations updatedFirestation = safetynetServiceController.saveFirestations(existingFirestation);
			controllerLogger.info("Firestation number "+stationNumber+ " updated");

			return ResponseEntity.ok(updatedFirestation);
	
		}
	}

	@DeleteMapping("firestation/{address}")
	public void deleteFirestation(@PathVariable String address) {
		safetynetServiceController.deleteFirestationsByAddress(address);
		controllerLogger.info("Firestations cover on "+address+" deleted");
	}

//medicalRecords modifier access

	@PostMapping("medicalRecord")
	public ResponseEntity<MedicalRecords> addMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
		MedicalRecords addedMedicalRecords = safetynetServiceController.saveMedicalRecords(medicalRecords);
		
		if (Objects.isNull(addedMedicalRecords)) {

			controllerLogger.error("the posted medical records request body is empty");
			return ResponseEntity.noContent().build();

		} else {

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
					.buildAndExpand(addedMedicalRecords.getFirstName(), addedMedicalRecords.getLastName()).toUri();
			controllerLogger.debug("Posted medical records "+ addedMedicalRecords.getFirstName()+" "+addedMedicalRecords.getLastName()+" URI created");
			controllerLogger.info("Posted medical records "+ addedMedicalRecords.getFirstName()+" "+addedMedicalRecords.getLastName()+" created");
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

			controllerLogger.info(firstName + " " + lastName + "'s medical records found");
			
			existingMedicalRecords.setAllergies(newMedicalRecordsDetails.getAllergies());
			existingMedicalRecords.setBirthDate(newMedicalRecordsDetails.getBirthDate());
			existingMedicalRecords.setMedications(newMedicalRecordsDetails.getMedications());

			MedicalRecords updatedMedicalRecords = safetynetServiceController.saveMedicalRecords(existingMedicalRecords);
			controllerLogger.info(firstName + " " + lastName + "'s medical updated");
			
			return ResponseEntity.ok(updatedMedicalRecords);
		}

	}

	@DeleteMapping("medicalRecord/{firstName}/{lastName}")
	public void deleteMedicalRecords(@PathVariable String firstName, @PathVariable String lastName) {
		safetynetServiceController.deleteMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		controllerLogger.info(firstName+" "+lastName+"'s medical records deleted");
	}

}
