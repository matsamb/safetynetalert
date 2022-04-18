package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@IdClass(com.safetynet.alert.entity.FloodStationsKey.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public @Data class FloodStations {

	String firstName;	
	String lastName;	
	String address;	
	int age;	
	String phone;
	int station;	
	@Id
	String medication;	
	@Id
	String allergy;
}
