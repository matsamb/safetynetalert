package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
@Embeddable
public class Medications implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//@Id 
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	String medication;
	
	//int id;
	
	public Medications() {}
	
	public Medications(String medication) {
		this.medication = medication;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	
	
}
