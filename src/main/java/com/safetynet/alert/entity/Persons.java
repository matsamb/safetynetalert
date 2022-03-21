package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Persons implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private or protected or package private for persistent variables
	@Id
	@JsonProperty("firstName")
	@Column(name="first_name")
	String firstName;

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

	public Persons() {
	}

	public Persons(PersonsKey personskey, String firstName, String lastName, String address, String city, Integer zip,
			String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;

		//this.personsKey = personskey;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persons other = (Persons) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) /*&& Objects.equals(personsKey, other.personsKey)*/
				&& Objects.equals(phone, other.phone) && Objects.equals(zip, other.zip);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, email, firstName, lastName, /*personsKey, */phone, zip);
	}

	
}
