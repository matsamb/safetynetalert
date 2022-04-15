package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.safetynet.alert.entity.Persons;

public interface PersonsRepository extends JpaRepository<Persons, String> {

	void deleteByFirstNameAndLastName(String firstName, String lastName);

	Persons findByFirstNameAndLastName(String firstName, String lastName);
	
	@Query(value = 
			"create table if not exists young (first_name varchar(50), last_name varchar(50), birth_date varchar (50), Age integer, address varchar(100), primary key (first_name, last_name));"
			,nativeQuery = true)
	void createTableYoung();
	
	@Query(value =
			"insert ignore into young (first_name, last_name, birth_date, Age, address) SELECT distinct medical_records.*, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.address from persons, medical_records where DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 < 18 and persons.first_name= medical_records.first_name;"
			,nativeQuery = true)
	void fillTableYoung();
}
