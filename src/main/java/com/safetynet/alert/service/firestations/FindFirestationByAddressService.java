package com.safetynet.alert.service.firestations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.FirestationsDAO;
import com.safetynet.alert.entity.Firestations;

@Service
public class FindFirestationByAddressService {

	static final Logger findFirestationByAddressServiceLogger = LogManager.getLogger("FindFirestationByAddressService");
	
	@Autowired
	FirestationsDAO firestationsDAOFindFirestationByAddressService;
	
	FindFirestationByAddressService(FirestationsDAO firestationsDAOFindFirestationByAddressService){
		this.firestationsDAOFindFirestationByAddressService = firestationsDAOFindFirestationByAddressService;
	}
	
	public Firestations findByAddress(String address) {
		findFirestationByAddressServiceLogger.debug("Searching for firestation covering "+address);
		return firestationsDAOFindFirestationByAddressService.findFirestationsByAddress(address);
	}
	
}
