package com.safetynet.alert.service.persons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;

@Service
public class DeletePersonByFirstNameAndLastNameService {

	static final Logger deletePersonByFirstNameAndLastNameServiceLogger = LogManager.getLogger("DeletePersonByFirstNameAndLastNameService");

	@Autowired
	PersonsDAO personsDAODeletePersonByFirstNameAndLastNameService;
	
	DeletePersonByFirstNameAndLastNameService(PersonsDAO personsDAODeletePersonByFirstNameAndLastNameService){
		this.personsDAODeletePersonByFirstNameAndLastNameService = personsDAODeletePersonByFirstNameAndLastNameService;
	}
	
	public void deleteByFirstNameAndLastName(String firstName, String lastName) {
		deletePersonByFirstNameAndLastNameServiceLogger.debug("Person "+firstName+" "+ lastName+" deleted");
		personsDAODeletePersonByFirstNameAndLastNameService.deletePersonsByFirstNameAndLastName(firstName, lastName);
	}
	
	
}
