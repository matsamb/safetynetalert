package com.safetynet.alert.service.persons;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@Service
public class FindPersonsByLastNameService {

	static final Logger findPersonsByLastNameServiceLogger = LogManager.getLogger("FindPersonsByLastNameService");

	@Autowired
	PersonsDAO personsDAOFindPersonsByLastNameService;
	
	FindPersonsByLastNameService(PersonsDAO personsDAOFindPersonsByLastNameService){
		this.personsDAOFindPersonsByLastNameService = personsDAOFindPersonsByLastNameService;
	}
	
	public List<Persons> findByLastName(String lastName) {
		findPersonsByLastNameServiceLogger.debug("Persons with last name "+lastName+" found");
		return personsDAOFindPersonsByLastNameService.findByLastName(lastName);
	}
	
}
