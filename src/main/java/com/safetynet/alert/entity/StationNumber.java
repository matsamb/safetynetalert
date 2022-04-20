package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

//@lombok.Generated
@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
public class StationNumber {

	@Id
	String firstName;
	
	@Id
	String lastName;
	
	String phone;

	
	String Address;
	
}
