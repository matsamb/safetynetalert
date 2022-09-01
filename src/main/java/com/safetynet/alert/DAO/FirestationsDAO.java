package com.safetynet.alert.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import com.safetynet.alert.entity.Firestations;

@Component
public class FirestationsDAO {

	private static List<Firestations> firestations = new ArrayList<>();

	
	public List<Firestations> findAllFirestations(){
		return List.copyOf(firestations);
	}
	
	public Firestations findFirestationsByAddress(String address) {
		Firestations result = new Firestations() ;
		for(Firestations p: FirestationsDAO.firestations) {
			if (address.contains(p.getAddress())) { 
				result=(Firestations)p.clone();
			}
		}
		return result; 
	}
	
	public List<Firestations> findFirestationsByStation(Integer station){
		List<Firestations> result = new ArrayList<>() ;
		for(Firestations p: List.copyOf(FirestationsDAO.firestations)) {
			if (Objects.equals(p.getStation(), station)) { 
				result.add((Firestations)p.clone());
			}
		}
		return result; 
	}
	
	public void saveFirestations(Firestations firestation) {
		firestations.add((Firestations)firestation.clone());
	}
	
	public void saveAllFirestations(List<Firestations> firestationsL) {
		firestationsL.forEach(p -> saveFirestations(p));
	}

	public void deleteFirestationsByAddress(String address){
		int i = 0;
		for(Firestations p: List.copyOf(FirestationsDAO.firestations)) {
			if (address.contains(p.getAddress())) { 
				FirestationsDAO.firestations.remove(i);
				i--;
			}
			i++;
		}
		
	} 
	
	public void deleteAll(){
		FirestationsDAO.firestations.clear();
		
	}
	
	
}
