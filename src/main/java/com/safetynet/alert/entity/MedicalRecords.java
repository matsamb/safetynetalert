package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class  MedicalRecords implements Serializable {

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
	
	/*@Column(name="global_entity", insertable = false, updatable = false)
	GlobalEntity globalEntity;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn (name="global_entity", referencedColumnName ="global_entity_Id")
	})
	public GlobalEntity getGlobalEntity() {
		return globalEntity;
	}

	public void setgGobalEntity(GlobalEntity globalEntity) {
		this.globalEntity = globalEntity;
	}*/
	/*
	public MedicalRecords() {}
	
	public MedicalRecords(String firstName, String lastName, String birthDate, Medications[] medications, Allergies[] allergies) {
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

	public Medications[] getMedications() {
		return medications;
	}

	public void ListMedications(Medications[] medications) {
		this.medications = medications;
	}

	public Allergies[] getAllergies() {
		return allergies;
	}

	public void ListAllergies(Allergies[] allergies) {
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
	
	
	*/
}
