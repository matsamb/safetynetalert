package com.safetynet.alert.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.DTO.FloodStationDTO;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.service.firestations.FindFirestationByStationService;
import com.safetynet.alert.service.persons.FindPersonByAddressService;
import com.safetynet.alert.service.records.FindMedicalRecordsByFirstNameAndLastNameService;

@RestController
public class FloodStationsUrlController {

	static final Logger floodStationsUrlControllerLogger = LogManager.getLogger("FloodStationsUrlController");

	@Autowired
	FindPersonByAddressService findPersonByAddressServiceFloodStationsUrlController;

	@Autowired
	FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceFloodStationsUrlController;

	@Autowired
	FindFirestationByStationService findFirestationByStationServiceFloodStationsUrlController;

	FloodStationsUrlController(FindPersonByAddressService findPersonByAddressServiceFloodStationsUrlController,
			FindMedicalRecordsByFirstNameAndLastNameService findMedicalRecordsByFirstNameAndLastNameServiceFloodStationsUrlController,
			FindFirestationByStationService findFirestationByStationServiceFloodStationsUrlController) {
		this.findPersonByAddressServiceFloodStationsUrlController = findPersonByAddressServiceFloodStationsUrlController;
		this.findMedicalRecordsByFirstNameAndLastNameServiceFloodStationsUrlController = findMedicalRecordsByFirstNameAndLastNameServiceFloodStationsUrlController;
		this.findFirestationByStationServiceFloodStationsUrlController = findFirestationByStationServiceFloodStationsUrlController;
	}

	@GetMapping("flood/stations") // ?stations=1,2,3,4
	public Iterable<FloodStationDTO> findFloodStations(@RequestParam(value = "stations") List<Integer> stations) {

		List<FloodStationDTO> resultList = new ArrayList<>();
		FloodStationDTO result = new FloodStationDTO();
		
		Calendar lendar = Calendar.getInstance();
		lendar.setTimeInMillis(System.currentTimeMillis()); 
		
		for (Integer s : stations) {
			floodStationsUrlControllerLogger.debug("requested station " + s + " under treatment");

			for (Firestations f : findFirestationByStationServiceFloodStationsUrlController.findByStation(s)) {
				floodStationsUrlControllerLogger.debug("Firestation " + f.getStation() + " " +f.getAddress() + " found");

				for (Persons p : findPersonByAddressServiceFloodStationsUrlController.findPersonByAddress(f.getAddress())) {
					floodStationsUrlControllerLogger.debug("person " + p.getFirstName() + " " +p.getLastName() + " found");
					result.setFirstName(p.getFirstName());
					result.setLastName(p.getLastName());
					result.setAddress(p.getAddress());
					result.setPhone(p.getPhone());
					for (MedicalRecords m : List.copyOf(findMedicalRecordsByFirstNameAndLastNameServiceFloodStationsUrlController
							.findByFirstNameAndLastName(p.getFirstName(), p.getLastName()))) {
						floodStationsUrlControllerLogger.debug("records " + p.getFirstName() + " " + p.getLastName() + " found");

						String[] date = m.getBirthDate().split("/");

						int y = lendar.get(Calendar.YEAR) - Integer.parseInt(date[2]);// Year
						int mo = lendar.get(Calendar.MONTH) - Integer.parseInt(date[0]);//month
						int d = lendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(date[1]);//day
						if (mo<0 || mo == 0 && d<0) {
							result.setAge(y);
						}else {
							result.setAge(y+1);
						}

						result.setMedication(Arrays.asList(m.getMedications()));
						result.setAllergy(Arrays.asList(m.getAllergies()));

					}
					resultList.add((FloodStationDTO) result.clone());
					floodStationsUrlControllerLogger.debug("Details of " + p.getFirstName() + " " +p.getLastName() + " added to resultList");

				}
			}
		}
		floodStationsUrlControllerLogger.info("FloodStation URL details for stations " + stations.toString() +" displayed");
		return resultList;
	}
}
