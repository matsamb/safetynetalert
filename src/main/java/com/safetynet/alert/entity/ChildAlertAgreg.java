package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

//@lombok.Generated
public class ChildAlertAgreg implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String firstName;
	
	
	private String lastName;
	
	private int age;
	
	private ChildAlert[] adult;

	public ChildAlertAgreg() {}
	
	public ChildAlertAgreg(String firstName, String lastName, int age, ChildAlert[] adult) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.adult = (ChildAlert[])adult.clone();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ChildAlert[] getAdult() {
		return (ChildAlert[])this.adult.clone();
	}

	public void setAdult(ChildAlert[] adult) {
		this.adult = (ChildAlert[])adult.clone();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(adult, age, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChildAlertAgreg other = (ChildAlertAgreg) obj;
		return adult==other.adult && age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	
	@Override
	public Object clone() {
		/*ChildAlertAgreg childAlertAgregClone = new ChildAlertAgreg(this.firstName, this.lastName, this.age, this.adult);	
		return childAlertAgregClone;*/
		Object childAlertAgregClone = null;
		try {
			childAlertAgregClone = super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		return childAlertAgregClone;
	}
	
	
	
}
