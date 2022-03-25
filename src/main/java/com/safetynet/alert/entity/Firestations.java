package com.safetynet.alert.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Firestations {
	
	@Id
	@JsonProperty("address")
	String address;
	
	@JsonProperty("station")
	Integer station;
		
}
