package com.safetynet.alert.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
public class DataTableName {
	
	//@Embedded
	@JsonProperty("persons")
	private Persons persons;
	//@Embedded
	@JsonProperty("firestations")
	private FireStations firestations;
	//@Embedded
	@JsonProperty("medicalrecords")
	private MedicalRecords medicalrecords;
	
	
	public Persons getPersons() {
		return persons;
	}
	public void setPersons(Persons persons) {
		this.persons = persons;
	}
	public FireStations getFirestations() {
		return firestations;
	}
	public void setFirestations(FireStations firestations) {
		this.firestations = firestations;
	}
	public MedicalRecords getMedicalrecords() {
		return medicalrecords;
	}
	public void setMedicalrecords(MedicalRecords medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	
}
