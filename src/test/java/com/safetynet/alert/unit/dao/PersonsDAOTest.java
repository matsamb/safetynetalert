package com.safetynet.alert.unit.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.DAO.PersonsDAO;
import com.safetynet.alert.entity.Persons;

@ExtendWith(MockitoExtension.class)
public class PersonsDAOTest {

	@InjectMocks
	private PersonsDAO personsDAO;
	
	@AfterEach
	public void clean() {
		personsDAO.deleteAllPersons();
	}
	
	@Test
	public void givenAEmptyList_whenSavingAPerson_ThenSizeShouldBe1() {
		
		Persons p1 = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");

		personsDAO.savePersons(p1);
		
		assertThat(personsDAO.findAllPersons()).hasSize(1);
		
	}
	
	@Test
	public void givenAEmptyList_whenSavingAListOf2Person_ThenSizeShouldBe2() {
		
		Persons p1 = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");
		Persons p3 = new Persons("Tenley","Boyd","1509 Culver St","Culver",97451,"841-874-6512","tenz@email.com");
		
		List<Persons> expectedPersons = new ArrayList<Persons>();
		expectedPersons.add(p1);
		expectedPersons.add(p3);
		
		personsDAO.saveAllPersons(expectedPersons);
		
		assertThat(personsDAO.findAllPersons()).hasSize(2);
		
	}
	
	@Nested
	class SpecialInit{
		
		@BeforeEach
		public void init() {
			
			Persons p1 = new Persons("John","Boyd","1509 Culver St","Culver",97451,"841-874-6512","jaboyd@email.com");
			Persons p3 = new Persons("Tenley","Boyd","1509 Culver St","Culver",97451,"841-874-6512","tenz@email.com");
		
			List<Persons> expectedPersons = new ArrayList<Persons>();
			expectedPersons.add(p1);
			expectedPersons.add(p3);
			
			personsDAO.saveAllPersons(expectedPersons);
			
		}
		
		@Test
		public void givenASize2List_whenDeletingAPersonThenSizeShouldBe1() {
		
			personsDAO.deletePersonsByFirstNameAndLastName("Tenley", "Boyd");
			
			assertThat(personsDAO.findAllPersons()).hasSize(1);
		}
		
		@Test
		public void givenAListOf2Persons_whenFindAllPersons_thenItShouldReturnAListOfSize2() {
			
			assertThat(personsDAO.findAllPersons())
				.isInstanceOf(List.class)
				.isNotEmpty()
				.hasSize(2);
			
		}
		
		@Test
		public void findPersonByFirstNameAndLastName_ShouldReturnFirstNameAndLastName() {
			
			Persons result = personsDAO.findByFirstNameAndLastName("John", "Boyd");
			
			assertThat(result.getFirstName()).contains("John");
			assertThat(result.getLastName()).isEqualTo("Boyd");
			
		}
		
		@Test
		public void givenAListOf2Persons1LastName_whenFindPersonsByLastName_thenItShouldreturnAListofSize2() {
			
			assertThat(personsDAO.findByLastName("Boyd"))
				.isInstanceOf(List.class)
				.hasSize(2);
			
		}
		
		@Test
		public void givenAListOf2Persons1City_whenFindPersonsByCity_thenItShouldreturnAListofSize2() {
			
			assertThat(personsDAO.findByCity("Culver"))
				.isInstanceOf(List.class)
				.hasSize(2);
			
		}
		
		@Test
		public void givenAListOf2Persons1Address_whenFindPersonsByAddress_thenItShouldreturnAListofSize2() {
			
			assertThat(personsDAO.findPersonsByAddress("1509 Culver St"))
				.isInstanceOf(List.class)
				.hasSize(2);
			
		}
		
	}
	
}
