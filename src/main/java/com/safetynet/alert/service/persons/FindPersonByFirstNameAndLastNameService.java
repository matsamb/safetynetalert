package com.safetynet.alert.service.persons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@Service
public class FindPersonByFirstNameAndLastNameService {

	static final Logger findPersonByFirstNameAndLastNameServiceLogger = LogManager.getLogger("FindPersonByFirstNameAndLastNameService");

	@Autowired
	PersonsDAO personsDAOFindPersonByFirstNameAndLastNameService;
	
	FindPersonByFirstNameAndLastNameService(PersonsDAO personsDAOFindPersonByFirstNameAndLastNameService){
		this.personsDAOFindPersonByFirstNameAndLastNameService = personsDAOFindPersonByFirstNameAndLastNameService;
	}
	
	public Persons findPersonsByFirstNameAndLastName(String firstName, String lastName) {
		findPersonByFirstNameAndLastNameServiceLogger.debug("Searching for "+firstName+" "+lastName);
		
		return personsDAOFindPersonByFirstNameAndLastNameService.findByFirstNameAndLastName(firstName, lastName);
	}
	
}
