package com.safetynet.alert;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alert.service.firestations.FirestationsDaoLoaderService;
import com.safetynet.alert.service.persons.PersonsDaoLoaderService;
import com.safetynet.alert.service.records.MedicalRecordsDaoLoaderService;

@SpringBootApplication
public class SafetynetalertApplication implements CommandLineRunner{

	private static final Logger safetynetalertApplicationLogger = LogManager.getLogger("SafetynetalertApplication");
	
	public static void main(String[] args) {
		safetynetalertApplicationLogger.info("Safetynetalert server is up");
		SpringApplication.run(SafetynetalertApplication.class, args);
	}

	@Autowired
	private PersonsDaoLoaderService personsDaoLoaderService;
	@Autowired
	private FirestationsDaoLoaderService firestationsDaoLoaderService;
	@Autowired
	private MedicalRecordsDaoLoaderService medicalRecordsDaoLoaderService;
	
	@Override
	public void run(String... args) throws Exception {

		
		personsDaoLoaderService.stringToDAO();
		firestationsDaoLoaderService.stringToDAO();
		medicalRecordsDaoLoaderService.stringToDAO();

		safetynetalertApplicationLogger.info("data loaded into Lists of DAOs");
	}
	
}
