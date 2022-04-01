package com.safetynet.alert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PhoneAlert {

	@Id
	String phone;
	
	int station;
}
