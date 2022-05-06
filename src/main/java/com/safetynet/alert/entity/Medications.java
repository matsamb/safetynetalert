package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medications implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String medication;

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
