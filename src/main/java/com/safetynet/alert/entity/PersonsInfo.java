package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@lombok.Generated
@IdClass(com.safetynet.alert.entity.FloodStationsKey.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public @Data class PersonsInfo {

	//@Id
	//int id;
	
	String firstName;
	
	@Id
	String lastName;
	
	String address;
	
	int age;
	
	String email;
	
	@Id
	String medication;
	
	@Id
	String allergy;
	
}
