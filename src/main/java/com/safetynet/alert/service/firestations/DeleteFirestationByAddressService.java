package com.safetynet.alert.service.firestations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.FirestationsDAO;

@Service
public class DeleteFirestationByAddressService {

	static final Logger deleteFirestationByAddressServiceLogger = LogManager.getLogger("DeleteFirestationByAddressService");
	
	@Autowired
	FirestationsDAO firestationsDAODeleteFirestationByAddressService;
	
	DeleteFirestationByAddressService(FirestationsDAO firestationsDAODeleteFirestationByAddressService){
		this.firestationsDAODeleteFirestationByAddressService = firestationsDAODeleteFirestationByAddressService;
	}
	
	public void deleteByAddress(String address) {
		deleteFirestationByAddressServiceLogger.debug("Firestation cover on "+address+" deleted");
		firestationsDAODeleteFirestationByAddressService.deleteFirestationsByAddress(address);
	}
	
}
