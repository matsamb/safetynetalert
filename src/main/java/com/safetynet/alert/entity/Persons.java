package com.safetynet.alert.entity;

import javax.persistence.Column;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persons implements Cloneable{

	@Column(name="first_name")
	String firstName;

	@JsonProperty("lastName")
	String lastName;

	@JsonProperty("address")
	String address;

	@JsonProperty("city")
	String city;

	@JsonProperty("zip")
	Integer zip;

	@JsonProperty("phone")
	String phone;

	@JsonProperty("email")
	String email;

	public Object clone() {
		Persons person = null;
		try {
			person = (Persons)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return person;
		
	}
}
