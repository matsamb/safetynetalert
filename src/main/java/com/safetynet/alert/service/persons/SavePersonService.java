package com.safetynet.alert.service.persons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@Service
public class SavePersonService {

	static final Logger savePersonServiceLogger = LogManager.getLogger("SavePersonService");

	@Autowired
	PersonsDAO personsDAOSavePersonService;
	
	SavePersonService(PersonsDAO personsDAOSavePersonService){
		this.personsDAOSavePersonService = personsDAOSavePersonService;
	}
	
	public void savePersons(Persons persons) {
		savePersonServiceLogger.debug(persons.getFirstName()+" "+persons.getLastName()+" saved");
		personsDAOSavePersonService.savePersons(persons);
	}
	
}
