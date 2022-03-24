package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OrderColumn;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
public class MedicalRecords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty("firstName")
	@Column(name="first_name")
	String firstName;
	@Id
	@JsonProperty("lastName")
	@Column(name="last_name")
	String lastName;
	
	@JsonProperty("birthdate")
	@Column(name="birth_date")
	String birthDate;
	
	//@Id
	@Embedded
	@JsonProperty("medications")
	@ElementCollection
	@OrderColumn
	List<Medications> medications; //= new HashList<>();
	
	//@Id
	@Embedded
	@JsonProperty("allergies")
	@ElementCollection
	@OrderColumn
	@Column(name="allergy", nullable=false,
    insertable=false, updatable=false)
	List<Allergies> allergies;// = new HashList<>();
	
	public MedicalRecords() {}
	
	public MedicalRecords(String firstName, String lastName, String birthDate, List<Medications> medications, List<Allergies> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public void ListFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void ListLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void ListBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<Medications> getMedications() {
		return medications;
	}

	public void ListMedications(List<Medications> medications) {
		this.medications = medications;
	}

	public List<Allergies> getAllergies() {
		return allergies;
	}

	public void ListAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergies, birthDate, firstName, lastName, medications);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecords other = (MedicalRecords) obj;
		return Objects.equals(allergies, other.allergies) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(medications, other.medications);
	}
	
	
	
}
