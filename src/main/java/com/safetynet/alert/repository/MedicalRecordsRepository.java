package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safetynet.alert.entity.MedicalRecords;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, String> {

	void deleteByFirstNameAndLastName(String firstName, String lastName);

	MedicalRecords getByFirstNameAndLastName(String firstName, String lastName);

}
