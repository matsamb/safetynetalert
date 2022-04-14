package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@lombok.Generated
@Embeddable
public class Medications implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public Object clone() {
		Object medicationsClone = null;
		try {
			medicationsClone = super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		return medicationsClone;
	}
	
}
