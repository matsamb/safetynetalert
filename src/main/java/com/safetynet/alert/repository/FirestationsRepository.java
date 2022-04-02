package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.alert.entity.Firestations;

public interface FirestationsRepository extends JpaRepository<Firestations, Integer> {

	void deleteByAddress(String address);

	Firestations getByStation(int stationNumber);

}
