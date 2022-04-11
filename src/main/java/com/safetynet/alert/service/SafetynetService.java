package com.safetynet.alert.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.entity.ChildAlert;
import com.safetynet.alert.entity.CommunityEmail;
import com.safetynet.alert.entity.FirePlaces;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.FloodStations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.entity.PersonsInfo;
import com.safetynet.alert.entity.PhoneAlert;
import com.safetynet.alert.entity.StationNumber;
import com.safetynet.alert.factory.SafetynetalertFactory;
import com.safetynet.alert.repository.ChildAlertRepository;
import com.safetynet.alert.repository.CommunityEmailRepository;
import com.safetynet.alert.repository.FirePlacesRepository;
import com.safetynet.alert.repository.FirestationsRepository;
import com.safetynet.alert.repository.FloodStationsRepository;
import com.safetynet.alert.repository.MedicalRecordsRepository;
import com.safetynet.alert.repository.PersonsInfoRepository;
import com.safetynet.alert.repository.PersonsRepository;
import com.safetynet.alert.repository.PhoneAlertRepository;
import com.safetynet.alert.repository.StationNumberRepository;

@Service
public class SafetynetService {

	static final Logger safetynetServiceLogger = LogManager.getLogger("safetynetService");

	@Autowired
	PersonsRepository personsServiceRepository;

	@Autowired
	MedicalRecordsRepository medicalRecordsServiceRepository;

	@Autowired
	FirestationsRepository firestationsServiceRepository;

	@Autowired
	StationNumberRepository stationNumberServiceRepository;
	
	@Autowired
	PhoneAlertRepository phoneAlertServiceRepository;
	
	@Autowired
	ChildAlertRepository childAlertServiceRepository;
	
	@Autowired
	CommunityEmailRepository communityEmailServiceRepository;
	
	@Autowired
	FirePlacesRepository firePlacesServiceRepository;
	
	@Autowired
	PersonsInfoRepository personsInfoServiceRepository;
	
	@Autowired
	FloodStationsRepository floodStationsServiceRepository;

	@Autowired
	SafetynetalertFactory serviceFactory;

	public SafetynetService(PersonsRepository safetynetalertRepository,
			MedicalRecordsRepository medicalRecordsServiceRepository,
			FirestationsRepository firestationsServiceRepository,
			StationNumberRepository stationNumberServiceRepository,
			PhoneAlertRepository phoneAlertServiceRepository,
			ChildAlertRepository childAlertServiceRepository,
			CommunityEmailRepository communityEmailServiceRepository,
			FirePlacesRepository firePlacesServiceRepository,
			PersonsInfoRepository personsInfoServiceRepository,
			FloodStationsRepository floodStationsServiceRepository,
			SafetynetalertFactory serviceFactory) {
		this.personsServiceRepository = safetynetalertRepository;
		this.medicalRecordsServiceRepository = medicalRecordsServiceRepository;
		this.firestationsServiceRepository = firestationsServiceRepository;
		this.stationNumberServiceRepository =stationNumberServiceRepository;
		this.phoneAlertServiceRepository =phoneAlertServiceRepository;
		this.childAlertServiceRepository = childAlertServiceRepository;
		this.communityEmailServiceRepository = communityEmailServiceRepository;
		this.firePlacesServiceRepository = firePlacesServiceRepository;
		this.personsInfoServiceRepository = personsInfoServiceRepository;
		this.floodStationsServiceRepository = floodStationsServiceRepository;
		this.serviceFactory = serviceFactory;
	}

//URL section 

	public Iterable<StationNumber> getCustomStationNumber(int stationNumber) {
		return stationNumberServiceRepository.getCustomStationNumberUrl(stationNumber);
	}
	
	public Iterable<PhoneAlert> getCustomPhoneAlert(int station) {
		return phoneAlertServiceRepository.getCustomPhoneAlertUrl(station);
	}

	public Iterable<ChildAlert> getCustomChildAlert(String address) {
		return childAlertServiceRepository.getCustomChildAlertUrl(address);
	}
	
	public Iterable<CommunityEmail> getCustomCommunityEmail(String city) {
		return communityEmailServiceRepository.getCustomCommunityEmailUrl(city);
	}

	public Iterable<FirePlaces> getFirePlaces(String address) {
		return firePlacesServiceRepository.getFirePlacesUrl(address);
	}

	public Iterable<PersonsInfo> getPersonsInfo(String firstName, String lastName) {
		return personsInfoServiceRepository.getPersonsInfoUrl(firstName, lastName);
	}
	
	public Iterable<FloodStations> getFloodStations(List<String> stationsTable) {
		return floodStationsServiceRepository.getFloodStationsUrl(stationsTable);
	}	

//URL modifiers
	
	
	
//IO	

	public String jsonToString() {
		Resource resource = serviceFactory.loadSafetynetAlertDataWithClassPathResource();
		List<String> jsonAsListOfStrings = null;

		try (BufferedReader finalReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"UTF-8"))) {
			jsonAsListOfStrings = finalReader.lines().collect(Collectors.toList());// .forEach(System.out::println);
			safetynetServiceLogger.info("file reader service get file content correctly");
		} catch (IOException e) {
			e.printStackTrace();
			safetynetServiceLogger.error("file reader service can not fetch file content");
		}

		String fJsonString = String.join("", jsonAsListOfStrings);
		safetynetServiceLogger.trace("json file content turned into a java string");
		String jsonString = fJsonString.replaceAll("\\[\\]", "\\[\"N_A\"\\]");

		return jsonString;
	}

// persons section

	public Persons savePersons(Persons persons) {
		return personsServiceRepository.save(persons);
	}
	
	public Persons findPersonsByFirstNameAndLastName(String firstName, String lastName) {
		return personsServiceRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	

	public void deletePersonsByFirstNameAndLastName(String firstName, String lastName) {
		personsServiceRepository.deleteByFirstNameAndLastName(firstName, lastName);
	}

	
	public void jsonToPersonsDatabase() {

		String jsonString = this.jsonToString();

		String personsJsonArrayString = jsonString.substring(jsonString.indexOf('['), jsonString.indexOf(']') + 1);
		// System.out.println(personsJsonArrayString);
		safetynetServiceLogger.trace("json persons array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = serviceFactory.getObjectMapper();// new ObjectMapper();

		Persons[] personsJavaArray = null;
		try {
			personsJavaArray = objectMapper.readValue(personsJsonArrayString, Persons[].class);
			safetynetServiceLogger.info("json persons array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			safetynetServiceLogger
					.error("JsonMappingException, json persons array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			safetynetServiceLogger
					.error("JsonProcessingException, json persons array as string can't be processed to java array");

		}
		
		if (Objects.nonNull(personsJavaArray)) {
		List<Persons> personsJavaList = Arrays.asList(personsJavaArray);
		personsServiceRepository.saveAll(personsJavaList);
		safetynetServiceLogger.info("json file persons content loaded into database");
		} else {
		safetynetServiceLogger.debug("json file persons content is null");
		}

	}

/*	public void saveAllPersons(Iterable<Persons> persons) {
		personsServiceRepository.saveAll(persons);
	}

	public Iterable<Persons> getAllPersons() {
		List<Persons> per = personsServiceRepository.findAll();
		List<Firestations> fir = firestationsServiceRepository.findAll();
		// per.forEach(System.out::println);
		per.get(0).getAddress();
		System.out.println(per.get(0).getAddress() + "\n" + fir.get(0));
		return personsServiceRepository.findAll();
	}*/

// medical records section	

	public MedicalRecords saveMedicalRecords(MedicalRecords medicalRecords) {
		return medicalRecordsServiceRepository.save(medicalRecords);
	}

	public MedicalRecords getMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		return medicalRecordsServiceRepository.getByFirstNameAndLastName(firstName, lastName);
	}

	public void deleteMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		medicalRecordsServiceRepository.deleteByFirstNameAndLastName(firstName, lastName);		
	}

	public void jsonToMedicalRecordsDatabaseTable() {

		String jsonString = this.jsonToString();

		String medicalrecordsJsonArrayString = jsonString.substring(
				jsonString.indexOf('[', jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1),
				jsonString.indexOf('}', jsonString.length() - 2));
		// System.out.println(medicalrecordsJsonArrayString);
		safetynetServiceLogger
				.trace("json medicalrecords array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = serviceFactory.getObjectMapper();//new ObjectMapper();

		MedicalRecords[] medicalrecordsJavaArray = null;
		try {
			medicalrecordsJavaArray = objectMapper.readValue(medicalrecordsJsonArrayString, MedicalRecords[].class);
			safetynetServiceLogger.info("json medicalrecords array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			safetynetServiceLogger
					.error("JsonMappingException, json medicalrecords array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			safetynetServiceLogger.error(
					"JsonProcessingException, json medicalrecords array as string can't be processed to java array");

		}
		
		if (Objects.nonNull(medicalrecordsJavaArray)) {
		List<MedicalRecords> medicalrecordsJavaList = Arrays.asList(medicalrecordsJavaArray);
		medicalRecordsServiceRepository.saveAll(medicalrecordsJavaList);
		safetynetServiceLogger.info("json file medicalrecords content loaded into database");
		} else {
			safetynetServiceLogger.debug("json file medicalrecords content is null");
		}

	}

/*	public void saveAllMedicalRecords(Iterable<MedicalRecords> medicalRecords) {
		medicalRecordsServiceRepository.saveAll(medicalRecords);
	}

	public Iterable<MedicalRecords> getAllMedicalRecords() {
		return medicalRecordsServiceRepository.findAll();
	}*/

//fire stations section


	public Firestations saveFirestations(Firestations firestation) {
		return firestationsServiceRepository.save(firestation);
	}
	
	public Firestations getFirestationsByStation(int stationNumber) {
		return firestationsServiceRepository.getByStation(stationNumber);
	}

	public void deleteFirestationsByAddress(String address) {
		firestationsServiceRepository.deleteByAddress(address);		
	}

	public void jsonToFireStationsDatabaseTable() {

		String jsonString = this.jsonToString();

		String firestationsJsonArrayString = jsonString.substring(jsonString.indexOf('[', jsonString.indexOf(']')),
				jsonString.indexOf(']', jsonString.indexOf(']') + 1) + 1);
		// System.out.println(firestationsJsonArrayString);
		safetynetServiceLogger
				.trace("json firestations array as string correctly extracted from json source file string");

		ObjectMapper objectMapper = serviceFactory.getObjectMapper();// new ObjectMapper();

		Firestations[] firestationsJavaArray = null;
		try {
			firestationsJavaArray = objectMapper.readValue(firestationsJsonArrayString, Firestations[].class);
			safetynetServiceLogger.info("json firestations array as string correctly mapped to java array");

		} catch (JsonMappingException m) {
			m.printStackTrace();
			safetynetServiceLogger
					.error("JsonMappingException, json firestations array as string can't be mapped to java array");

		} catch (JsonProcessingException p) {
			p.printStackTrace();
			safetynetServiceLogger.error(
					"JsonProcessingException, json firestations array as string can't be processed to java array");

		}
		
		if (Objects.nonNull(firestationsJavaArray)) {
		List<Firestations> firestationsJavaList = Arrays.asList(firestationsJavaArray);
		firestationsServiceRepository.saveAll(firestationsJavaList);
		safetynetServiceLogger.info("json file firestations content loaded into database");
		}else {
			safetynetServiceLogger.debug("json file firestations content is null");
		}
	}

/*	public void saveAllFirestations(Iterable<Firestations> firestations) {
		firestationsServiceRepository.saveAll(firestations);
	}

	public Iterable<Firestations> getAllFirestations() {
		return firestationsServiceRepository.findAll();
	}*/

}