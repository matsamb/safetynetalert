package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
//@Data
public class GlobalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*define link to person
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="global_entity_id")
	int globalEntityId;*/
	
	@Id
	@JsonProperty("firstName")
	@Column(name="first_name", insertable = false, updatable = false)
	String firstName;

	@Id
	@Column(name="last_name", insertable = false, updatable = false)
	@JsonProperty("lastName")
	String lastName;

	
	@JsonProperty("address")
	@Column(name="address", insertable = false, updatable = false)
	String address;

	@JsonProperty("city")
	@Column(name="city", insertable = false, updatable = false)
	String city;

	@JsonProperty("zip")
	@Column(name="zip", insertable = false, updatable = false)
	Integer zip;

	@JsonProperty("phone")
	@Column(name="phone", insertable = false, updatable = false)
	String phone;

	@JsonProperty("email")
	@Column(name="email", insertable = false, updatable = false)
	String email;
	
	//define link to medical records
	
	@JsonProperty("birthdate")
	@Column(name="birth_date", insertable = false, updatable = false)
	String birthDate;
	
	@Embedded
	@JsonProperty("medications")
	@ElementCollection
	@OrderColumn
	Medications[] medications;
	
	@Embedded
	@JsonProperty("allergies")
	@ElementCollection
	@OrderColumn
	Allergies[] allergies;
	
	//define link to firestations
	
	@JsonProperty("station")
	@Column(name="station", insertable = false, updatable = false)
	Integer station;
	/*
	//@OneToOne
	Persons person;
	
	//@ManyToOne
	Firestations firestation;
	
	//@OneToOne
	MedicalRecords medicalRecord;*/
	
	public GlobalEntity() {}
	
	public GlobalEntity(Persons person, Firestations firestation, MedicalRecords medicalRecord, String firstName, String lastName, String address, String city, Integer zip, String phone,
			String email, String birthDate, Medications[] medications, Allergies[] allergies, Integer station) {
		/*this.person = person;
		this.firestation = firestation;
		this.medicalRecord = medicalRecord;*/
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.birthDate = birthDate;
		this.medications = medications;
		this.allergies = allergies;
		this.station = station;
	}
	/*
	@OneToOne
	@JoinColumns({
		@JoinColumn (name="first_name", referencedColumnName ="first_name")
		,@JoinColumn (name="last_name", referencedColumnName ="last_name")
		,@JoinColumn (name="address", referencedColumnName ="address")
		,@JoinColumn (name="city", referencedColumnName ="city")
		,@JoinColumn (name="zip", referencedColumnName ="zip")
		,@JoinColumn (name="phone", referencedColumnName ="phone")
		,@JoinColumn (name="email", referencedColumnName ="email")
	})
	public Persons getPerson() {
		return person;
	}

	public void setPerson(Persons person) {
		this.person = person;
	}

	@ManyToOne
	@JoinColumns({
		@JoinColumn (name="address", referencedColumnName ="address")
		,@JoinColumn (name="station", referencedColumnName ="station")
	})
	public Firestations getFirestation() {
		return firestation;
	}

	public void setFirestation(Firestations firestation) {
		this.firestation = firestation;
	}

	@OneToOne
	@JoinColumns({
		@JoinColumn (name="first_name", referencedColumnName ="first_name")
		,@JoinColumn (name="last_name", referencedColumnName ="last_name")
		,@JoinColumn (name="birth_date", referencedColumnName ="birth_date")
		,@JoinColumn (name="medications", referencedColumnName ="medications")
		,@JoinColumn (name="allergies", referencedColumnName ="allergies")
	})
	public MedicalRecords getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecords medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
	public int getGlobalEntityId() {
		return globalEntityId;
	}*/
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Medications[] getMedications() {
		return medications;
	}

	public void setMedications(Medications[] medications) {
		this.medications = medications;
	}

	public Allergies[] getAllergies() {
		return allergies;
	}

	public void setAllergies(Allergies[] allergies) {
		this.allergies = allergies;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GlobalEntity other = (GlobalEntity) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	
}
