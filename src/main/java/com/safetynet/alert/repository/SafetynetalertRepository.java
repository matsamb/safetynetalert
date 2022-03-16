package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.entity.Persons;

@Repository
public interface SafetynetalertRepository extends JpaRepository<Persons, String> {

}
