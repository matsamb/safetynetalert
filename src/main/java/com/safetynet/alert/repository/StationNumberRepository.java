package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.StationNumber;

public interface StationNumberRepository extends JpaRepository<StationNumber, String> {
	
	@Query(value="select persons.first_name, persons.last_name, persons.phone, medical_records.birth_date, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.address, firestations.station from persons, medical_records, firestations where firestations.station = :stationNumber and persons.address = firestations.address and medical_records.first_name = persons.first_name and medical_records.last_name = persons.last_name;",nativeQuery=true)
	Iterable<StationNumber> getCustomStationNumberUrl(@Param("stationNumber") int stationNumber);
}
