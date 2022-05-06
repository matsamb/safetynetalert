package com.safetynet.alert.DTO;

import lombok.Data;


@Data
public class StationNumberDTO implements Cloneable {

	private String firstName;
	
	private String lastName;
	
	private String phone;

	private String Address;
	
	public Object clone() {
		
		StationNumberDTO copy = null;
		try {
			copy = (StationNumberDTO)super.clone();
			
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
}
