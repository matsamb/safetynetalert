package com.safetynet.alert.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Disabled;
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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.safetynet.alert.controller.SafetynetalertController;
import com.safetynet.alert.entity.Allergies;
import com.safetynet.alert.entity.Firestations;
import com.safetynet.alert.entity.MedicalRecords;
import com.safetynet.alert.entity.Medications;
import com.safetynet.alert.entity.Persons;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:applicationIT.properties")
public class SafetyNetAlertIntegrationTests {

	@LocalServerPort
	private int port;
	
	//@Autowired
	//private MockMvc mockMvc;

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
		String coverageDetails = "[{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"phone\":\"841-874-9888\",\"birthDate\":\"03/15/1965\",\"age\":57,\"station\":4,\"address\":\"112 Steppes Pl\"},{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"phone\":\"841-874-9845\",\"birthDate\":\"03/06/1994\",\"age\":28,\"station\":4,\"address\":\"489 Manchester St\"},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"phone\":\"841-874-8888\",\"birthDate\":\"04/06/1965\",\"age\":57,\"station\":4,\"address\":\"112 Steppes Pl\"},{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"phone\":\"841-874-6874\",\"birthDate\":\"03/06/1994\",\"age\":28,\"station\":4,\"address\":\"112 Steppes Pl\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/firestation/4", String.class))
				.contains(coverageDetails);
	}

	@Test
	public void givenPhoneAlertStationOneUrlWhenServerUpThenItShouldReturnPhoneAlertStationOneDetails()
			throws Exception {
		String phoneAlertStationOneDetails = "[{\"phone\":\"841-874-6512\",\"station\":1},{\"phone\":\"841-874-7462\",\"station\":1},{\"phone\":\"841-874-7784\",\"station\":1},{\"phone\":\"841-874-7784\",\"station\":1},{\"phone\":\"841-874-7784\",\"station\":1},{\"phone\":\"841-874-8547\",\"station\":1}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/phoneAlert/1", String.class))
				.contains(phoneAlertStationOneDetails);
	}

	@Test
	public void givenChildAlert1509CulverStUrlWhenServerUpThenItShouldReturnChildAlert1509CulverStDetails()
			throws Exception {
		String childAlert1509CulverStDetails = "[{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"birthDate\":\"01/08/1986\",\"age\":36,\"address\":\"1509 Culver St\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthDate\":\"03/06/1989\",\"age\":33,\"address\":\"1509 Culver St\"},{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"birthDate\":\"03/06/1984\",\"age\":38,\"address\":\"1509 Culver St\"},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"birthDate\":\"09/06/2017\",\"age\":4,\"address\":\"1509 Culver St\"},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"birthDate\":\"02/18/2012\",\"age\":10,\"address\":\"1509 Culver St\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/childAlert/1509 Culver St",
				String.class)).contains(childAlert1509CulverStDetails);
	}

	@Test
	public void givenCommunityEmailCulverWhenServerUpThenItShouldReturnCommunityEmailDetails() throws Exception {
		String communityEmailDetails = "[{\"email\":\"aly@imail.com\",\"city\":\"Culver\"},{\"email\":\"bstel@email.com\",\"city\":\"Culver\"},{\"email\":\"clivfd@ymail.com\",\"city\":\"Culver\"},{\"email\":\"drk@email.com\",\"city\":\"Culver\"},{\"email\":\"gramps@email.com\",\"city\":\"Culver\"},{\"email\":\"jaboyd@email.com\",\"city\":\"Culver\"},{\"email\":\"jpeter@email.com\",\"city\":\"Culver\"},{\"email\":\"lily@email.com\",\"city\":\"Culver\"},{\"email\":\"reg@email.com\",\"city\":\"Culver\"},{\"email\":\"soph@email.com\",\"city\":\"Culver\"},{\"email\":\"ssanw@email.com\",\"city\":\"Culver\"},{\"email\":\"tcoop@ymail.com\",\"city\":\"Culver\"},{\"email\":\"tenz@email.com\",\"city\":\"Culver\"},{\"email\":\"ward@email.com\",\"city\":\"Culver\"},{\"email\":\"zarc@email.com\",\"city\":\"Culver\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/communityEmail/Culver",
				String.class)).contains(communityEmailDetails);
	}

	@Test
	public void givenFire951LoneTreeRdWhenServerUpThenItShouldReturnFireDetails() throws Exception {
		String fireDetails = "[{\"id\":1,\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"address\":\"951 LoneTree Rd\",\"phone\":\"841-874-7458\",\"medication\":\"tradoxidine:400mg\",\"allergy\":\"N_A\",\"station\":2}]";

		assertThat(
				this.alertRestTemplate.getForObject("http://localhost:" + port + "/fire/951 LoneTree Rd", String.class))
						.contains(fireDetails);

	}

	@Test
	public void givenPersonsInfoSophiaZemicksWhenServerUpThenItShouldReturnPersonsInfoDetails() throws Exception {
		String personsInfoDetails = "[{\"id\":1,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"terazine:500mg\",\"allergy\":\"peanut\"},{\"id\":2,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"pharmacol:5000mg\",\"allergy\":\"peanut\"},{\"id\":3,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"hydrapermazol:900mg\",\"allergy\":\"peanut\"},{\"id\":4,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"peanut\"},{\"id\":5,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"shellfish\"},{\"id\":6,\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"age\":34,\"email\":\"soph@email.com\",\"medication\":\"aznol:60mg\",\"allergy\":\"aznol\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/personsInfo/Sophia&Zemicks",
				String.class)).contains(personsInfoDetails);

	}

	@Test
	public void givenFloodStationOneWhenServerUpThenItShouldReturnFloodDetails() throws Exception {
		String floodDetails = "[{\"id\":1,\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"age\":42,\"phone\":\"841-874-8547\",\"station\":1,\"medication\":\"thradox:700mg\",\"allergy\":\"illisoxian\"},{\"id\":2,\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":8,\"phone\":\"841-874-7784\",\"station\":1,\"medication\":\"pharmacol:2500mg\",\"allergy\":\"N_A\"},{\"id\":3,\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":8,\"phone\":\"841-874-7784\",\"station\":1,\"medication\":\"noxidian:100mg\",\"allergy\":\"N_A\"},{\"id\":4,\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"age\":40,\"phone\":\"841-874-7462\",\"station\":1,\"medication\":\"N_A\",\"allergy\":\"N_A\"},{\"id\":5,\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"age\":21,\"phone\":\"841-874-6512\",\"station\":1,\"medication\":\"N_A\",\"allergy\":\"shellfish\"},{\"id\":6,\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":46,\"phone\":\"841-874-7784\",\"station\":1,\"medication\":\"ibupurin:200mg\",\"allergy\":\"nillacilan\"},{\"id\":7,\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"age\":46,\"phone\":\"841-874-7784\",\"station\":1,\"medication\":\"hydrapermazol:400mg\",\"allergy\":\"nillacilan\"}]";

		assertThat(this.alertRestTemplate.getForObject("http://localhost:" + port + "/flood/1", String.class))
				.contains(floodDetails);

	}

//Persons modifier
	
	@Test
	// @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void givenAPersonWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/person/new";
		URI createdUri = new URI(postURL);

		Persons newPerson = new Persons("Max", "Body", "112 Steppes Pl", "Culver", 97451, "841-875-9218",
				"mady@imail.com");

		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Persons> postRequest = new HttpEntity<>(newPerson, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/person/delete/" + newPerson.getFirstName() + "/"
				+ newPerson.getLastName());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		
	}

	@Test
	// @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void givenAMedicalRecordsWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/medicalRecord";
		URI createdUri = new URI(postURL);

		MedicalRecords createdMedicalRecord = new MedicalRecords("Max", "Body", "03/15/1965", null, null);
		Medications medication = new Medications("N/A"); 
		Medications[] na = {medication};
		createdMedicalRecord.setMedications(na);

		Allergies allergy = new Allergies("N/A");
		Allergies[] al = {allergy};
		createdMedicalRecord.setAllergies(al);
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<MedicalRecords> postMedicalRequest = new HttpEntity<>(createdMedicalRecord, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postMedicalRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/medicalRecord/delete/" + createdMedicalRecord.getFirstName() + "/"
				+ createdMedicalRecord.getLastName());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		
	}
	
	
	@Test
	// @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void givenAFirestationWhenPostedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/firestation/new";
		URI createdUri = new URI(postURL);

		Firestations createdStationRecord = new Firestations("11 Max St", 5);
		
		HttpHeaders postHeaders = new HttpHeaders();
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Firestations> postMedicalRequest = new HttpEntity<>(createdStationRecord, postHeaders);

		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postMedicalRequest,
				String.class);

		this.alertRestTemplate.delete("http://localhost:" + port + "/firestation/delete/" + createdStationRecord.getAddress());
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		
	}

	

	
	@Test
	public void givenAPersonWhenUpdatedThenItShouldSucceed() throws Exception {

		final String postURL = "http://localhost:" + port + "/person/new";
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
		
		ResponseEntity<Persons> actualPerson = this.alertRestTemplate.exchange("http://localhost:"+port+"/person/update/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), HttpMethod.PUT, putRequest, Persons.class) ; //.put (, Persons.class, newPerson);

		this.alertRestTemplate.delete("http://localhost:"+port+"/person/delete/"+newPerson.getFirstName()+"/"+newPerson.getLastName());

		
		assertThat(201).isEqualTo(postEntity.getStatusCodeValue());
		assertThat(200).isEqualTo(actualPerson.getStatusCodeValue());	
		assertThat(actualPerson.getBody()).isEqualTo(updatedPerson);		

	}
	
	@Test
	public void givenAPersonWhenDeletedThenItShouldNotBeFound() throws Exception {

		final String postURL = "http://localhost:" + port + "/person/new";
		URI createdUri = new URI(postURL);

		Persons newPerson = new Persons("Max", "Body", "112 Steppes Pl", "Culver", 97451, "841-875-9218",
				"mady@imail.com");

		HttpHeaders postHeaders = new HttpHeaders(); 
		postHeaders.set("X-COM-PERSIST", "true");
		postHeaders.set("X-COM-LOCATION", "true");

		HttpEntity<Persons> postRequest = new HttpEntity<>(newPerson, postHeaders);
		ResponseEntity<String> postUnderTest = this.alertRestTemplate.postForEntity(createdUri, postRequest,
				String.class);
		
		this.alertRestTemplate.delete("http://localhost:"+port+"/person/delete/"+newPerson.getFirstName()+"/"+newPerson.getLastName());
		ResponseEntity<Persons> missingPerson = this.alertRestTemplate.getForEntity("http://localhost:"+port+"/person/new/"+newPerson.getFirstName()+"/"+newPerson.getLastName(), Persons.class, newPerson);
		
		assertThat(201).isEqualTo(postUnderTest.getStatusCodeValue());
		assertThat(404).isEqualTo(missingPerson.getStatusCodeValue());
	}

//Firestations modifier

}
