package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="firestations")
public @Data class FireStations {

	@JsonProperty("address")
	private String address;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("station")
	private Integer station;
	
}
