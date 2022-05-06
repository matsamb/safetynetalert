package com.safetynet.alert.DTO;

import java.util.List;

import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.Medications;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FloodStationDTO implements Cloneable{

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private int age;
	
	private String phone;

	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private List<Medications> medication;

	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private List<Allergies> allergy;
	
	public List<Medications> getMedication() {
		return List.copyOf(medication);
	}
	
	public void setMedication(List<Medications> medication) {
		this.medication = List.copyOf(medication);
	}
	
	public List<Allergies> getAllergy() {
		return List.copyOf(allergy);
	}
	
	public void setAllergy(List<Allergies> allergy) {
		this.allergy = List.copyOf(allergy);
	}
	
	public Object clone() {
		FloodStationDTO copy = null;
		try {
			copy = (FloodStationDTO)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		copy.setAllergy(List.copyOf(this.getAllergy()));
		copy.setMedication(List.copyOf(this.getMedication()));
		
		return copy;	
	}
	
	
}
