package com.safetynet.alert.entity;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Component
@Data
public class  MedicalRecords implements Cloneable {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("birthdate")
	private String birthDate;
	
	@JsonProperty("medications")
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private Medications[] medications;
	
	@JsonProperty("allergies")
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private Allergies[] allergies;
	
	public MedicalRecords() {}
	
	public MedicalRecords(String firstName, String lastName, String birthDate, Medications[] medications, Allergies[] allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.medications = (Medications[])medications.clone();
		this.allergies = (Allergies[])allergies.clone();
	}

	public Medications[] getMedications() {
		return (Medications[])this.medications.clone();
	}

	public void setMedications(Medications[] medications) {
		this.medications = (Medications[])medications.clone();
	}

	public Allergies[] getAllergies() {
		return (Allergies[])this.allergies.clone();
	}

	public void setAllergies(Allergies[] allergies) {
		this.allergies = (Allergies[])allergies.clone();
	}	

	
	
	public Object clone() {
		MedicalRecords copy = null;
		try {
			copy = (MedicalRecords) super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		copy.setMedications((Medications[])medications.clone());
		copy.setAllergies((Allergies[])allergies.clone());
		
		return copy;		
	}


}
