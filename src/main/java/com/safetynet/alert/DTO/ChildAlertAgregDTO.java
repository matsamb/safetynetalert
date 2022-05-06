package com.safetynet.alert.DTO;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ChildAlertAgregDTO implements Cloneable{
	
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private List<ChildAlertDTO> child;
	
	@Getter(value=AccessLevel.NONE)
	@Setter(value=AccessLevel.NONE)
	private List<ChildAlertDTO> adult;
	
	public List<ChildAlertDTO> getChild() {
		return List.copyOf(child);
	}
	
	public void setChild(List<ChildAlertDTO> child) {
		this.child = List.copyOf(child);
	}
	
	public List<ChildAlertDTO> getAdult() {
		return List.copyOf(adult);
	}
	
	public void setAdult(List<ChildAlertDTO> adult) {
		this.adult = List.copyOf(adult);
	}
	
	public Object clone() {
		ChildAlertAgregDTO copy = null;
			
		try {
			copy = (ChildAlertAgregDTO)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		copy.setAdult(List.copyOf(this.getChild()));
		copy.setChild(List.copyOf(this.getChild()));
		return copy;
	}
	
}
