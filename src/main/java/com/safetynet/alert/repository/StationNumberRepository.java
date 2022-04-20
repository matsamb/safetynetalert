package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.StationNumber;

public interface StationNumberRepository extends JpaRepository<StationNumber, String> {
	
	@Query(value="select persons.first_name, persons.last_name, persons.phone, persons.address from persons, firestations where firestations.station = :stationNumber and persons.address = firestations.address;",nativeQuery=true)
	Iterable<StationNumber> getCustomStationNumberUrl(@Param("stationNumber") int stationNumber);
}
