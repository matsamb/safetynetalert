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
public class Allergies implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String allergy;

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
