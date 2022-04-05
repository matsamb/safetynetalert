package com.safetynet.alert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.FloodStations;

public interface FloodStationsRepository extends JpaRepository<FloodStations, Integer> {

	@Query(value = "SELECT *, ROW_NUMBER() OVER(ORDER BY medication DESC) AS id FROM (SELECT DISTINCT persons.first_name, persons.last_name, persons.address, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS age, persons.phone, firestations.station, medical_records_medications.medication, medical_records_allergies.allergy FROM firestations, medical_records_allergies, medical_records_medications, medical_records,persons where firestations.station = :stationTable AND persons.address = firestations.address AND medical_records_allergies.medical_records_first_name = persons.first_name AND medical_records_allergies.medical_records_last_name = persons.last_name AND medical_records_medications.medical_records_first_name = persons.first_name AND medical_records_medications.medical_records_last_name = persons.last_name and medical_records.first_name = persons.first_name AND medical_records.last_name = persons.last_name GROUP BY medical_records_medications.medication  UNION SELECT DISTINCT persons.first_name, persons.last_name, persons.address, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS age, persons.phone, firestations.station, medical_records_medications.medication, medical_records_allergies.allergy FROM firestations, medical_records_allergies, medical_records_medications, medical_records,persons where firestations.station = :stationTable AND persons.address = firestations.address AND medical_records_allergies.medical_records_first_name = persons.first_name AND medical_records_allergies.medical_records_last_name = persons.last_name AND medical_records_medications.medical_records_first_name = persons.first_name AND medical_records_medications.medical_records_last_name = persons.last_name and medical_records.first_name = persons.first_name AND medical_records.last_name = persons.last_name GROUP BY medical_records_allergies.allergy) AS a;",nativeQuery = true)
	Iterable<FloodStations> getFloodStationsUrl(@Param("stationTable") List<String> stationTable);

	
}
