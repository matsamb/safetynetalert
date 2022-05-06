package com.safetynet.alert.service.persons;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@Service
public class FindPersonsByCityService {

	static final Logger findPersonsByCityServiceLogger = LogManager.getLogger("FindPersonsByCityService");

	@Autowired
	PersonsDAO personsDAOFindPersonsByCityService;
	
	FindPersonsByCityService(PersonsDAO personsDAOFindPersonsByCityService) {
		this.personsDAOFindPersonsByCityService = personsDAOFindPersonsByCityService;
	}
	
	public List<Persons> findByCity(String city) {
		findPersonsByCityServiceLogger.debug("Searching "+city+" inhabitants");
		return personsDAOFindPersonsByCityService.findByCity(city);		
	}
	
}
