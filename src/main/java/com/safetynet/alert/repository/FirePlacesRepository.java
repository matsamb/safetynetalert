package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.FirePlaces;

public interface FirePlacesRepository extends JpaRepository<FirePlaces, Integer> {

	@Query(value = "select *, row_number() over(order by medication desc) as id from (select distinct persons.first_name, persons.last_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.address, firestations.station, persons.phone, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons, firestations WHERE firestations.address = :address and persons.address = firestations.address and medical_records_allergies.medical_records_first_name= persons.first_name and medical_records_allergies.medical_records_last_name= persons.last_name  and medical_records_allergies.medical_records_first_name= medical_records_medications.medical_records_first_name and medical_records.first_name = medical_records_medications.medical_records_first_name group by medical_records_medications.medication UNION select distinct persons.first_name, persons.last_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.address, firestations.station, persons.phone, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons, firestations WHERE firestations.address = :address and persons.address = firestations.address and medical_records_allergies.medical_records_first_name= persons.first_name and medical_records_allergies.medical_records_last_name= persons.last_name  and medical_records_allergies.medical_records_first_name= medical_records_medications.medical_records_first_name and medical_records.first_name = medical_records_medications.medical_records_first_name group by medical_records_allergies.allergy) as a;",nativeQuery = true)
	Iterable<FirePlaces> getFirePlacesUrl(@Param(value = "address") String address);

}
