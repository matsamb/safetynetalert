package com.safetynet.alert;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.safetynet.alert.service.SafetynetService;

@SpringBootApplication
public class SafetynetalertApplication implements CommandLineRunner{

	private static final Logger logger = LogManager.getLogger("alertApp");
	
	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertApplication.class, args);
	}
	
	@Autowired
	private SafetynetService safetynetService;

	@Override
	public void run(String... args) throws Exception {

		safetynetService.jsonToPersonsDatabase();
		safetynetService.jsonToMedicalRecordsDatabaseTable();		
		safetynetService.jsonToFireStationsDatabaseTable();
		logger.info("data loaded into database");
	}
	
}
