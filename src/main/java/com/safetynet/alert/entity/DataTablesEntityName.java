package com.safetynet.alert.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DataTablesEntityName {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	@JsonProperty("persons")
	@Embedded
	Iterable<Persons> persons;
	//private Firestations firestations;

}
