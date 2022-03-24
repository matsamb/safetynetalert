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
public class Allergies implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	String allergy;
	
	//int id;
	

	public Allergies() {}
	
	public Allergies(String allergy) {
		this.allergy = allergy;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
	
	
}
