package com.safetynet.alert.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Firestations implements Cloneable {
	
	@JsonProperty("address")
	String address;
	
	@JsonProperty("station")
	Integer station;
	
	public Object clone() {
		Firestations copy = null;
		
		try {
			copy = (Firestations) super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return copy;
	}
}
