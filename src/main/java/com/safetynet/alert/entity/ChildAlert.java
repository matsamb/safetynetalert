package com.safetynet.alert.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@lombok.Generated
@IdClass(com.safetynet.alert.entity.PersonsKey.class)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlert implements Serializable, Cloneable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String firstName;
	
	@Id
	String lastName;
	
	int age;

/*	@Override
	public Object clone() {
		
		Object childAlertClone = null;
		try {
			childAlertClone = super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		return childAlertClone;
		
	}*/
}
