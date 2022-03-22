package com.safetynet.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.entity.Persons;
import com.safetynet.alert.repository.SafetynetalertPersonsRepository;
import com.safetynet.alert.service.SafetynetalertPersonsService;

@RestController
public class SafetynetalertController {

	@Autowired
	private SafetynetalertPersonsService safetynetalertPersonsServiceController;
	
	public SafetynetalertController(SafetynetalertPersonsService safetynetalertPersonsServiceController) {
		this.safetynetalertPersonsServiceController = safetynetalertPersonsServiceController;
	}
	
	@GetMapping("/persons")/*{stationNumber*///}
	public Iterable<Persons> getAllPersons(){
		return safetynetalertPersonsServiceController.getAllPersons() ;
	}
	/*
	@GetMapping("/childAlert{address}")
	
	@GetMapping("/phoneAlert{station}")
	
	@GetMapping("/fire{address}")
	
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
