package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

public final class PersonsKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String firstName;
	
	public String lastName;
	
	public PersonsKey() {}
	
	public PersonsKey(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonsKey other = (PersonsKey) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, firstName);
	}

	@Override
	public String toString() {
		return "Persons Key [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
