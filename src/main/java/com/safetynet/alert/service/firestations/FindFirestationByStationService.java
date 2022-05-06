package com.safetynet.alert.service.firestations;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.DAO.FirestationsDAO;
import com.safetynet.alert.entity.Firestations;

@Service
public class FindFirestationByStationService {

	static final Logger findFirestationByStationServiceLogger = LogManager.getLogger("FindFirestationByStationService");
	
	@Autowired
	FirestationsDAO firestationsDAOFindFirestationByStationService;
	
	FindFirestationByStationService(FirestationsDAO firestationsDAOFindFirestationByStationService){
		this.firestationsDAOFindFirestationByStationService = firestationsDAOFindFirestationByStationService;
	}
	
	public List<Firestations> findByStation(Integer station) {
		findFirestationByStationServiceLogger.info("Firestation "+station+" recovered");
		return firestationsDAOFindFirestationByStationService.findFirestationsByStation(station);
		
	}
	
}
