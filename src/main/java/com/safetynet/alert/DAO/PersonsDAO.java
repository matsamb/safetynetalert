package com.safetynet.alert.DAO;

import java.util.ArrayList;
import java.util.List;

import com.safetynet.alert.entity.Persons;

public class PersonsDAO {

	public static List<Persons> persons = new ArrayList<>();
	
	public List<Persons> findAll(){
		return List.copyOf(persons);
	}
	
	public void savePersons(Persons person) {
		persons.add((Persons)person.clone());
	}
	
	public void saveAllPersons(List<Persons> personL) {
		personL.forEach(p -> savePersons(p));
	}
}
