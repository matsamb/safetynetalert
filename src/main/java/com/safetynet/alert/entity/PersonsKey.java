package com.safetynet.alert.entity;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public final class PersonsKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// properties public or class protected
	
	public String firstName;
	
	public String lastName;
	
	public PersonsKey() {}
	
	public PersonsKey(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.firstName = lastName;
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
		return Objects.equals(firstName, other.firstName) && Objects.equals(firstName, other.firstName);
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, firstName);
	}

	@Override
	public String toString() {
		return "PersonsKey [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
