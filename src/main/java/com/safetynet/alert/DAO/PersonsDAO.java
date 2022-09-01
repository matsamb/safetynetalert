package com.safetynet.alert.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.safetynet.alert.entity.Persons;

@Component
public class PersonsDAO { 
	
	private static final Logger LOGGER= LogManager.getLogger("PersonsDAO");
	
	private static List<Persons> persons = new ArrayList<>();
		
	public List<Persons> findAllPersons(){
		LOGGER.info(PersonsDAO.persons);
		return List.copyOf(PersonsDAO.persons);
	}
/////////	
	public Persons findByFirstNameAndLastName(String firstName, String lastName){
		Persons result = new Persons() ;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if (firstName.contains(p.getFirstName()) && lastName.contains(p.getLastName())) { 
				result = (Persons)p.clone();
			}
		}
		return result; 
	}
/////////	
	public List<Persons> findByLastName(String lastName){
		List<Persons> result = new ArrayList<>() ;
		
			for(Persons p: List.copyOf(PersonsDAO.persons)) {
				if ( lastName.contains(p.getLastName())) { 
					result.add((Persons) p.clone());
				}
			}
		
		return result; 
	}
//////////	
	public List<Persons> findPersonsByAddress(String address){
		LOGGER.info(PersonsDAO.persons);
		List<Persons> result = new ArrayList<>() ;
		for(Persons p: List.copyOf(PersonsDAO.persons)) {
			if ( address.contains(p.getAddress())) { 
				LOGGER.info(p);
				result.add((Persons)p.clone());
			}
		}
		LOGGER.info(result);
		return result; 
	}
	
	public List<Persons> findByCity(String city) {
		List<Persons> result = new ArrayList<>() ;
		for(Persons p: PersonsDAO.persons) {
			if ( city.contains(p.getCity())) { 
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
			if (firstName.contains(p.getFirstName()) && lastName.contains(p.getLastName()) ) { 
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
