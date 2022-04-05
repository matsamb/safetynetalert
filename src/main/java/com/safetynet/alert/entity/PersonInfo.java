package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonInfo {

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
@Id	
	String firstName;
	
@Id
	String lastName;
	
	String birthDate;
	
	int age;
	
	String email;
	
	String medication;
	
	String allergy;
	
	
}
