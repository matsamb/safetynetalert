package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public @Data class PersonsInfo {

	@Id
	int id;
	
	String firstName;
	
	String lastName;
	
	String address;
	
	int age;
	
	String email;
	
	String medication;
	
	String allergy;
	
}
