package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.safetynet.alert.entity.Persons;

public interface SafetynetalertPersonsRepository extends JpaRepository<Persons, String> {

}
