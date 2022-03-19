package com.safetynet.alert.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration
public class SafetyNetAlertFileReadingIntegrationTests {
/*	
	
	@Test
	void givenHelloInaFile_WhenReadFileIsCalled_ThenItShouldReturnHello(){
		
		String jsonReference
		
		= "{"
						+	"\"persons\": ["
+"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }" 
				                      +"  ], "
				        +   "  \"firestations\": [ "
				        +   "  { \"address\":\"1509 Culver St\", \"station\":\"3\" } "
				           				+"     ],"
				         + "   \"medicalrecords\": [ "
+ "{ \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] } "
				             		+"			] "
				             	+"}"
   ;
		
		String filepath = "/safetynetalert/src/main/resources/jsonTestFile.json";
		
		AlertFileReaderService alertFileReader = new AlertFileReaderService(filepath); 
		
		String result = alertFileReader.getContent();//.getPersons();
		System.out.println(result);
		assertThat(result).isEqualTo(jsonReference);
	} */
}
