package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.PersonsInfo;

public interface PersonsInfoRepository extends JpaRepository<PersonsInfo, Integer>{

	@Query( value = 
			"SELECT DISTINCT persons.first_name, persons.last_name, persons.address, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS age, persons.email, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons where persons.first_name= :firstName and persons.last_name= :lastName  and medical_records_allergies.medical_records_first_name = persons.first_name AND medical_records_allergies.medical_records_last_name = persons.last_name AND medical_records_medications.medical_records_first_name = persons.first_name AND medical_records_medications.medical_records_last_name = persons.last_name and medical_records.first_name = persons.first_name AND medical_records.last_name = persons.last_name group by medical_records_medications.medication UNION SELECT DISTINCT persons.first_name, persons.last_name, persons.address, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS age, persons.email, medical_records_medications.medication, medical_records_allergies.allergy from medical_records_allergies, medical_records_medications, medical_records,persons where persons.first_name= :firstName and persons.last_name= :lastName  and medical_records_allergies.medical_records_first_name = persons.first_name AND medical_records_allergies.medical_records_last_name = persons.last_name AND medical_records_medications.medical_records_first_name = persons.first_name AND medical_records_medications.medical_records_last_name = persons.last_name and medical_records.first_name = persons.first_name AND medical_records.last_name = persons.last_name group by medical_records_allergies.allergy;"
			, nativeQuery = true)
	Iterable<PersonsInfo> getPersonsInfoUrl(
			@Param ("firstName") String firstName,
			@Param ("lastName") String lastName);

}
