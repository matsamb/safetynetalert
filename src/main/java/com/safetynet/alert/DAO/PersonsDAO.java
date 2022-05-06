package com.safetynet.alert.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import com.safetynet.alert.entity.Persons;

@Component
public class PersonsDAO { 
	
	private static List<Persons> persons = new ArrayList<>();
		
	public List<Persons> findAllPersons(){
		return List.copyOf(PersonsDAO.persons);
	}
/////////	
	public Persons findByFirstNameAndLastName(String firstName, String lastName){
		Persons result = new Persons() ;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if (Objects.equals(p.getFirstName(),firstName) && Objects.equals(p.getLastName(), lastName) ) { 
				result = (Persons)p.clone();
			}
		}
		return result; 
	}
/////////	
	public List<Persons> findByLastName(String lastName){
		List<Persons> result = new ArrayList<>() ;
		
			for(Persons p: List.copyOf(PersonsDAO.persons)) {
				if ( Objects.equals(p.getLastName() , lastName )) { 
					result.add((Persons) p.clone());
				}
			}
		
		return result; 
	}
//////////	
	public List<Persons> findPersonsByAddress(String address){
		List<Persons> result = new ArrayList<>() ;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if ( Objects.equals(p.getAddress(), address )) { 
				result.add((Persons)p.clone());
			}
		}
		return result; 
	}
	
	public List<Persons> findByCity(String city) {
		List<Persons> result = new ArrayList<>() ;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if ( Objects.equals(p.getCity(), city )) { 
				result.add((Persons)p.clone());
			}
		}
		return result;
	}
	
	public void savePersons(Persons person) {
		persons.add((Persons)person.clone());
	}
	
	public void saveAllPersons(List<Persons> personL) {
		personL.forEach(p -> savePersons(p));
	}
	
	public void deletePersonsByFirstNameAndLastName(String firstName, String lastName){
		int i = 0;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if (Objects.equals(p.getFirstName(),firstName) && Objects.equals(p.getLastName(),lastName) ) { 
				PersonsDAO.persons.remove(i);
				i--;
			}
			i++;
		}
		
	}
	
	public void deleteAllPersons(){
		PersonsDAO.persons.clear();	
	}
	
}
