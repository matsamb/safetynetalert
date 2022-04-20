package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Arrays;

import lombok.NoArgsConstructor;

//@lombok.Generated
@NoArgsConstructor
public class ChildAlertAgregV2 implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ChildAlert[] child;
	private ChildAlert[] adult;
	
/*	public ChildAlertAgregV2(ChildAlert[] child, ChildAlert[] adult) {
		super();
		this.child = (ChildAlert[])child.clone();
		this.adult =(ChildAlert[]) adult.clone();
	}*/

	public ChildAlert[] getChild() {
		return (ChildAlert[])this.child.clone();
	}

	public void setChild(ChildAlert[] child) {
		this.child = (ChildAlert[])child.clone();
	}

	public ChildAlert[] getAdult() {
		return (ChildAlert[])this.adult.clone();
	}

	public void setAdult(ChildAlert[] adult) {
		this.adult = (ChildAlert[])adult.clone();
	}

/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(adult);
		result = prime * result + Arrays.hashCode(child);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChildAlertAgregV2 other = (ChildAlertAgregV2) obj;
		return adult==other.adult && child==other.child;
	}
	
	*/
	
	
	
}
