package com.safetynet.alert.DTO;

import lombok.Data;

@Data
public class ChildAlertDTO implements Cloneable{

	private String firstName;
	
	private String lastName;
	
	private int age;
	
	public Object clone() {
		ChildAlertDTO copy = null;
		try {
			copy = (ChildAlertDTO)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
}
