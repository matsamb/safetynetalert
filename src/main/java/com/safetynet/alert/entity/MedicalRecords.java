package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//@lombok.Generated
@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
public class  MedicalRecords implements Serializable, Cloneable {

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
	
	public MedicalRecords() {}
	
	public MedicalRecords(String firstName, String lastName, String birthDate, Medications[] medications, Allergies[] allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.medications = (Medications[])medications.clone();
		this.allergies = (Allergies[]) allergies.clone();
	}

	public String getFirstName() {
		return this.firstName ;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Medications[] getMedications() {
		return (Medications[]) this.medications.clone();
	}

	public void setMedications(Medications[] medications) {
		this.medications = (Medications[])medications.clone();
	}

	public Allergies[] getAllergies() {
		return (Allergies[]) this.allergies.clone();
	}

	public void setAllergies(Allergies[] allergies) {
		this.allergies = (Allergies[]) allergies.clone();
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
		return allergies == other.allergies && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& medications == other.medications;
	}

}
