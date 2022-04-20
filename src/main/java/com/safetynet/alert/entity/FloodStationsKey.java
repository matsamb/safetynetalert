package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

//@lombok.Generated
public class FloodStationsKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//String firstName;
	

	String lastName;
	
	String medication;	
	
	String allergy;
	
	FloodStationsKey(){}
/*
	public FloodStationsKey(String medication, String allergy) {
		super();
		this.medication = medication;
		this.allergy = allergy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergy, medication);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FloodStationsKey other = (FloodStationsKey) obj;
		return Objects.equals(allergy, other.allergy) && Objects.equals(medication, other.medication);
	}

	@Override
	public String toString() {
		return "FloodStationsKey [medication=" + medication + ", allergy=" + allergy + "]";
	}
	
	*/
	
}
