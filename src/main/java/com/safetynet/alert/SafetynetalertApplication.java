package com.safetynet.alert;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetynetalertApplication implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger("alertApp");
	
	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		logger.info("SafetyNet'Alert Spring Boot App is running.");
	}

}
