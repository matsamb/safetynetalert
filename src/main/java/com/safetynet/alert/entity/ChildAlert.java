package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
public class ChildAlert {

	@Id
	String firstName;
	
	@Id
	String lastName;
	
	String birthDate;
	
	int age;
	
	String address;
}
