package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safetynet.alert.entity.Persons;

public interface PersonsRepository extends JpaRepository<Persons, String> {

	void deleteByFirstNameAndLastName(String firstName, String lastName);

	Persons findByFirstNameAndLastName(String firstName, String lastName);

}
