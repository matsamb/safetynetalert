package com.safetynet.alert.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Allergies{

	String allergy;

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
