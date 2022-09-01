package com.safetynet.alert.service.persons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@Service
public class FindPersonByAddressService {

	static final Logger findPersonByAddressServiceLogger = LogManager.getLogger("FindPersonByAddressService");

	@Autowired
	PersonsDAO personsDAOFindPersonByAddressService;
	
	FindPersonByAddressService(PersonsDAO personsDAOFindPersonByAddressService) {
		this.personsDAOFindPersonByAddressService = personsDAOFindPersonByAddressService;
	}
	
	public List<Persons> findPersonByAddress(String address) {
		findPersonByAddressServiceLogger.debug("Searching persons living at "+address);
			
		findPersonByAddressServiceLogger.info(personsDAOFindPersonByAddressService.findAllPersons());

		return personsDAOFindPersonByAddressService.findPersonsByAddress(address);
}	
	
	
}
