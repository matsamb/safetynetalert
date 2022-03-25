package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DataTablesEntityName implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	@JsonProperty("persons")
	@Embedded
	@ElementCollection
	@OrderColumn
	Persons[] persons;
	//private Firestations firestations;
	@JsonProperty("firestations")
	@Embedded
	@ElementCollection
	@OrderColumn
	Firestations[] firestations;
	
	@JsonProperty("medicalrecords")
	@Embedded
	@ElementCollection
	@OrderColumn
	MedicalRecords[] medicalrecords;
	
	public DataTablesEntityName(Persons[] persons, Firestations[] firestations, MedicalRecords[] medicalrecords) {
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}

	
}
