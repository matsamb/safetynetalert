package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public @Data class FirePlaces {

	@Id
	int id;
	
	String firstName;
	
	String lastName;
	
	String address;
	
	int Station;
	
	String phone;
	
	String medication;
	
	String allergy;
}
