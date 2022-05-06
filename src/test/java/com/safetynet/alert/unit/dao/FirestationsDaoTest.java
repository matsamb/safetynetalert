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

import com.safetynet.alert.DAO.FirestationsDAO;
import com.safetynet.alert.entity.Firestations;

@ExtendWith(MockitoExtension.class)
public class FirestationsDaoTest {

	@InjectMocks
	private FirestationsDAO firestationsDAOTest;
	
	@AfterEach
	public void clean() {
		
		firestationsDAOTest.deleteAll();
		
	}
	
	@Test
	public void givenAEmptyList_whenSavingAFirestationThenSizeShouldBe1() {
		
		Firestations f = new Firestations("1509 Culver St",3);
		List<Firestations> lf = new ArrayList<>();
		lf.add(f);
		
		firestationsDAOTest.saveFirestations(f);
		
		assertThat(firestationsDAOTest.findAllFirestations()).hasSize(1);
		
	}
	
	@Test
	public void givenAEmptyList_whenSavingAListOf2FirestationThenSizeShouldBe2() {
		
		Firestations f = new Firestations("1509 Culver St",3);
		Firestations w = new Firestations("112 Steppes Pl",4);
		List<Firestations> lf = new ArrayList<>();
		lf.add(f);
		lf.add(w);
		
		firestationsDAOTest.saveAllFirestations(lf);
		
		assertThat(firestationsDAOTest.findAllFirestations()).hasSize(2);
		
	}
	
	@Nested
	class SpecialInit{
		
		@BeforeEach
		public void init() {
			
			Firestations f = new Firestations("1509 Culver St",3);
			Firestations w = new Firestations("112 Steppes Pl",4);
			List<Firestations> lf = new ArrayList<>();
			lf.add(f);
			lf.add(w);
			
			firestationsDAOTest.saveAllFirestations(lf);
			
		}
		
		@Test
		public void givenASize2List_whenDeletingAFirestationThenSizeShouldBe1() {
		
			firestationsDAOTest.deleteFirestationsByAddress("112 Steppes Pl");
			
			assertThat(firestationsDAOTest.findAllFirestations()).hasSize(1);
		
		}
		
		@Test
		public void givenASize2List_whenDeletingAllFirestationsThenSizeShouldBe0() {
		
			firestationsDAOTest.deleteAll();
			
			assertThat(firestationsDAOTest.findAllFirestations()).hasSize(0);
		
		}
		
		@Test
		public void findAllFirestations_ShouldReturnAListOfSize2() {
			
			assertThat(firestationsDAOTest.findAllFirestations())
				.isInstanceOf(List.class)
				.isNotEmpty()
				.hasSize(2);
			
		}
		
		@Test
		public void findFirestationsByStation_ShouldReturnAListOfSize1() {
			
			assertThat(firestationsDAOTest.findFirestationsByStation(3))
				.isInstanceOf(List.class)
				.isNotEmpty()
				.hasSize(1);
			
		}
		
		@Test
		public void findFirestationsByAddress112SteppesPl_ShouldReturnStation4() {
			
			assertThat(firestationsDAOTest.findFirestationsByAddress("112 Steppes Pl").getStation())
				.isEqualTo(4);

		}
		
	}
	
	
	
}
