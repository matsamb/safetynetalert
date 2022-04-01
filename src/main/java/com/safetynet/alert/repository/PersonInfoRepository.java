package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.PersonInfo;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, String> {

	@Query(value = "select distinct medical_records.*,DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.email, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons where medical_records_allergies.medical_records_first_name= :firstName  and medical_records_allergies.medical_records_last_name= :lastName  and medical_records_allergies.medical_records_first_name= medical_records_medications.medical_records_first_name and medical_records.first_name = medical_records_medications.medical_records_first_name group by medical_records_medications.medication UNION select distinct medical_records.*,DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.email, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons where medical_records_allergies.medical_records_first_name= :firstName  and medical_records_allergies.medical_records_last_name= :lastName  and medical_records_allergies.medical_records_first_name= medical_records_medications.medical_records_first_name and medical_records.first_name = medical_records_medications.medical_records_first_name group by medical_records_allergies.allergy;", nativeQuery = true)
	Iterable<PersonInfo> getPersonInfoUrl(@Param(value = "firstName") String firstName,
			@Param(value = "lastName") String lastName);

}
