package com.safetynet.alert.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.safetynet.alert.controller.SafetynetalertController;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;
import com.safetynet.alert.entity.Persons;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SafetyNetAlertIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate alertRestTemplate;

	@Autowired
	private SafetynetalertController alertController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(alertController).isNotNull();
	}

	@Test
	public void givenHomeUrlWhenServerUpThenItShouldReturnWelcomeMessage() throws Exception {
		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/home", String.class))
				.contains("WELCOME TO SAFETY NET' ALERT.");
	}

	@Test
	public void givenFirestationNumberFourUrlWhenServerUpThenItShouldReturnStationFourCoverageDetails()
			throws Exception {
		String coverageDetails = "[{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"phone\":\"841-874-9888\",\"address\":\"112 Steppes Pl\"},{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"phone\":\"841-874-9845\",\"address\":\"489 Manchester St\"},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"phone\":\"841-874-8888\",\"address\":\"112 Steppes Pl\"},{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"phone\":\"841-874-6874\",\"address\":\"112 Steppes Pl\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/firestation?stationNumber=4", String.class))
				.contains(coverageDetails);
	}

	@Test
	public void givenPhoneAlertStationOneUrlWhenServerUpThenItShouldReturnPhoneAlertStationOneDetails()
			throws Exception {
		String phoneAlertStationOneDetails = "[{\"phone\":\"841-874-6512\"},{\"phone\":\"841-874-7462\"},{\"phone\":\"841-874-7784\"},{\"phone\":\"841-874-7784\"},{\"phone\":\"841-874-7784\"},{\"phone\":\"841-874-8547\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/phoneAlert?station=1", String.class))
				.contains(phoneAlertStationOneDetails);
	}

	@Test
	public void givenChildAlert1509CulverStUrlWhenServerUpThenItShouldReturnChildAlert1509CulverStDetails()
			throws Exception {
		String childAlert1509CulverStDetails = "{\"child\":[{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"age\":4},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"age\":10}],\"adult\":[{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"age\":36},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"age\":33},{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"age\":38}]}"; 

				assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/childAlert?address=1509 Culver St",
				String.class)).contains(childAlert1509CulverStDetails);
	}

	@Test
	public void givenCommunityEmailCulverWhenServerUpThenItShouldReturnCommunityEmailDetails() throws Exception {
		String communityEmailDetails = "[{\"email\":\"aly@imail.com\"},{\"email\":\"bstel@email.com\"},{\"email\":\"clivfd@ymail.com\"},{\"email\":\"drk@email.com\"},{\"email\":\"gramps@email.com\"},{\"email\":\"jaboyd@email.com\"},{\"email\":\"jpeter@email.com\"},{\"email\":\"lily@email.com\"},{\"email\":\"reg@email.com\"},{\"email\":\"soph@email.com\"},{\"email\":\"ssanw@email.com\"},{\"email\":\"tcoop@ymail.com\"},{\"email\":\"tenz@email.com\"},{\"email\":\"ward@email.com\"},{\"email\":\"zarc@email.com\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/communityEmail?city=Culver",
				String.class)).contains(communityEmailDetails);
	}

	@Test
	public void givenFire951LoneTreeRdWhenServerUpThenItShouldReturnFireDetails() throws Exception {
		String fireDetails = "[{\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"phone\":\"841-874-7458\",\"medication\":\"tradoxidine:400mg\",\"allergy\":\"N_A\",\"station\":2}]";

		assertThat(
				this.alertRestTemplate.getForObject("http://localhost:" + port + "/fire?address=951 LoneTree Rd", String.class))
						.contains(fireDetails);

	}

	@Test
	public void givenPersonsInfoSophiaZemicksWhenServerUpThenItShouldReturnPersonsInfoDetails() throws Exception {
		String personsInfoDetails = "[{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"peanut\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"hydrapermazol:900mg\",\"allergy\":\"peanut\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"pharmacol:5000mg\",\"allergy\":\"peanut\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"terazine:500mg\",\"allergy\":\"peanut\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"shellfish\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"aznol\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/personInfo?firstName=sophia&lastName=zemicks",
				String.class)).contains(personsInfoDetails);

	}

	@Test
	public void givenFloodStationOneWhenServerUpThenItShouldReturnFloodDetails() throws Exception {
		String floodDetails = "[{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":46,\"phone\":\"841-874-7784\",\"medication\":\"ibupurin:200mg\",\"allergy\":\"nillacilan\"},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":46,\"phone\":\"841-874-7784\",\"medication\":\"hydrapermazol:400mg\",\"allergy\":\"nillacilan\"},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"age\":40,\"phone\":\"841-874-7462\",\"medication\":\"N_A\",\"allergy\":\"N_A\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":8,\"phone\":\"841-874-7784\",\"medication\":\"noxidian:100mg\",\"allergy\":\"N_A\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":8,\"phone\":\"841-874-7784\",\"medication\":\"pharmacol:2500mg\",\"allergy\":\"N_A\"},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"age\":42,\"phone\":\"841-874-8547\",\"medication\":\"thradox:700mg\",\"allergy\":\"illisoxian\"},{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"age\":21,\"phone\":\"841-874-6512\",\"medication\":\"N_A\",\"allergy\":\"shellfish\"}]";
		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/flood/stations?stations=1", String.class))
				.contains(floodDetails);

	}

//Persons modifier
	
	@Test
	public void givenAPersonWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/person";
		URI createdUri = new URI(postURL);

		Persons newPerson = new Persons("Max", "Body", "112 Steppes Pl", "Culver", 97451, "841-875-9218",
				"mady@imail.com");

		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Persons> postRequest = new HttpEntity<>(newPerson, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/person/" + newPerson.getFirstName() + "/"
				+ newPerson.getLastName());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
	}

	
	
	
	@Test
	public void givenAMedicalRecordsWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/medicalRecord";
		URI createdUri = new URI(postURL);

		Medications medication = new Medications("N/A"); 
		Medications[] na = {medication};

		Allergies allergy = new Allergies("N/A");
		Allergies[] al = {allergy};

		MedicalRecords createdMedicalRecord = new MedicalRecords("Max", "Body", "03/15/1965", na, al);
		
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<MedicalRecords> postMedicalRequest = new HttpEntity<>(createdMedicalRecord, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postMedicalRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/medicalRecord/" + createdMedicalRecord.getFirstName() + "/"
				+ createdMedicalRecord.getLastName());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		
	}
	
	
	@Test
	public void givenAFirestationWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/firestation";
		URI createdUri = new URI(postURL);

		Firestations createdStation = new Firestations("11 Max St", 5);
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Firestations> postStationRequest = new HttpEntity<>(createdStation, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postStationRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/firestation/" + createdStation.getAddress());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());		
	}
	
	@Test
	public void givenAPersonWhenUpdatedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/person";
		URI createdUri = new URI(postURL);

		Persons newPerson = new Persons("Max", "Body", "112 Steppes Pl", "Culver", 97451, "841-875-9218",
				"mady@imail.com");

		HttpHeaders postHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Persons> postRequest = new HttpEntity<>(newPerson, postHeaders);
		ResponseEntity<String> postEntity = this.alertRestTemplate.postForEntity(createdUri, postRequest,
				String.class);
		
		Persons updatedPerson = new Persons("Max", "Body", "908 73rd St", "Culver", 97451, "841-875-9218","mady@imail.com");
		HttpHeaders putHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");
		HttpEntity<Persons> putRequest = new HttpEntity<>(updatedPerson, putHeaders);
		
		// this.alertRestTemplate.getForEntity("http://localhost:"+port+"/person/new/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), putRequest, String.class);
		
		ResponseEntity<Persons> actualPerson = this.alertRestTemplate.exchange("http://localhost:"+port+"/person/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), HttpMethod.PUT, putRequest, Persons.class) ; //.put (, Persons.class, newPerson);

		this.alertRestTemplate.delete("http://localhost:"+port+"/person/"+newPerson.getFirstName()+"/"+newPerson.getLastName());

		
		assertThat(201).isEqualTo(postEntity.getStatusCodeValue());
		assertThat(200).isEqualTo(actualPerson.getStatusCodeValue());	
		assertThat(actualPerson.getBody()).isEqualTo(updatedPerson);		

	}
	
	@Test
	public void givenAFirestationWhenUpdatedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/firestation";
		URI createdUri = new URI(postURL);

		Firestations createdStation = new Firestations("11 Max St", 5);
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Firestations> postMedicalRequest = new HttpEntity<>(createdStation, postHeaders);

		ResponseEntity<String> postEntity = this.alertRestTemplate.postForEntity(createdUri, postMedicalRequest,
				String.class);
		
		Firestations updatedFirestation = new Firestations("11 Max St", 6);
		HttpHeaders putHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");
		HttpEntity<Firestations> putRequest = new HttpEntity<>(updatedFirestation, putHeaders);
		
		// this.alertRestTemplate.getForEntity("http://localhost:"+port+"/person/new/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), putRequest, String.class);
		
		ResponseEntity<Firestations> actualFirestation = this.alertRestTemplate.exchange("http://localhost:"+ port +"/firestation/5"/*+ createdStation.getStation()*/, HttpMethod.PUT, putRequest, Firestations.class);

		this.alertRestTemplate.delete("http://localhost:"+port+"/firestation/"+createdStation.getAddress());

		
		assertThat(201).isEqualTo(postEntity.getStatusCodeValue());
		assertThat(200).isEqualTo(actualFirestation.getStatusCodeValue());	
		assertThat(actualFirestation.getBody()).isEqualTo(updatedFirestation);		

	}
	
	@Test
	public void givenAMedicalRecordWhenUpdatedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/medicalRecord";
		URI createdUri = new URI(postURL);

		Medications medication = new Medications("N/A"); 
		Medications[] na = {medication};

		Allergies allergy = new Allergies("N/A");
		Allergies[] al = {allergy};
		
		MedicalRecords createdMedicalRecord = new MedicalRecords("Max", "Body", "03/15/1965", na, al);
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<MedicalRecords> postMedicalRequest = new HttpEntity<>(createdMedicalRecord, postHeaders);

		ResponseEntity<String> postRecord = this.alertRestTemplate.postForEntity(createdUri, postMedicalRequest,
				String.class);
		
		MedicalRecords updatedRecords = new MedicalRecords("Max", "Body", "908 73rd St", na, al);
		HttpHeaders putHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");
		HttpEntity<MedicalRecords> putRequest = new HttpEntity<>(updatedRecords, putHeaders);
		
		// this.alertRestTemplate.getForEntity("http://localhost:"+port+"/person/new/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), putRequest, String.class);
		
		ResponseEntity<MedicalRecords> actualRecord = this.alertRestTemplate.exchange("http://localhost:"+port+"/medicalRecord/"+createdMedicalRecord.getFirstName()+"/"+createdMedicalRecord.getLastName(), HttpMethod.PUT, putRequest, MedicalRecords.class) ; //.put (, Persons.class, newPerson);

		this.alertRestTemplate.delete("http://localhost:"+port+"/medicalRecord/"+createdMedicalRecord.getFirstName()+"/"+createdMedicalRecord.getLastName());

		
		assertThat(201).isEqualTo(postRecord.getStatusCodeValue());
		assertThat(200).isEqualTo(actualRecord.getStatusCodeValue());	
		assertThat(actualRecord.getBody().equals(updatedRecords));		

	}
	
	@Test
	public void givenAPersonWhenDeletedThenItShouldNotBeFound() throws Exception {

		final String postURL = "http://localhost:" + port + "/person";
		URI createdUri = new URI(postURL);

		Persons newPerson = new Persons("Max", "Body", "112 Steppes Pl", "Culver", 97451, "841-875-9218",
				"mady@imail.com");

		HttpHeaders postHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Persons> postRequest = new HttpEntity<>(newPerson, postHeaders);
		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postRequest,
				String.class);
		
		this.alertRestTemplate.delete("http://localhost:"+port+"/person/Max/Body");
		ResponseEntity<Persons> missingPerson = this.alertRestTemplate.getForEntity("http://localhost:"+port+"/person/Max/Body", Persons.class, newPerson);
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		assertThat(200).isEqualTo(missingPerson.getStatusCodeValue());
		assertThat(missingPerson.getBody()).isNull();
	}	
	
}
