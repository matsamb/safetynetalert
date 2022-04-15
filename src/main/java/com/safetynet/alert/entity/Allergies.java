package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Allergies implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Override
	public Object clone() {
		Object allergiesClone = null;
		try {
			allergiesClone = super.clone();
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}		
		return allergiesClone;
	}
	
}
