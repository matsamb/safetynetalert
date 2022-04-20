package com.safetynet.alert.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@lombok.Generated
@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persons implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("firstName")
	@Column(name="first_name")
	String firstName;
	@Id
	@Column(name="last_name")
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

}
