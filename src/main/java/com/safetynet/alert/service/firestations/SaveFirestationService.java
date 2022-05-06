package com.safetynet.alert.service.firestations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.FirestationsDAO;
import com.safetynet.alert.entity.Firestations;

@Service
public class SaveFirestationService {

	static final Logger saveFirestationServiceLogger = LogManager.getLogger("SaveFirestationService");
	
	@Autowired
	FirestationsDAO firestationsDAOSaveFirestationService;
	
	SaveFirestationService(FirestationsDAO firestationsDAOSaveFirestationService){
		this.firestationsDAOSaveFirestationService = firestationsDAOSaveFirestationService;
	}
	
	public void saveFirestation(Firestations firestation) {
		saveFirestationServiceLogger.debug("Firestation "+firestation.getStation()+" saved");
		firestationsDAOSaveFirestationService.saveFirestations(firestation);
	}
	
}
