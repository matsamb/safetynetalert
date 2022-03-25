package com.safetynet.alert.entity;

import javax.persistence.Embeddable;


@Embeddable
public class Medications {

	String medication;
	
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
