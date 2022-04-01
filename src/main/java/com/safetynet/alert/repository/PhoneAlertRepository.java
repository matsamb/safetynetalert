package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.PhoneAlert;

public interface PhoneAlertRepository extends JpaRepository<PhoneAlert, String> {

	@Query(value="select persons.phone, firestations.station from persons, firestations where firestations.station= :station and persons.address = firestations.address order by last_name asc ;",nativeQuery = true)
	Iterable<PhoneAlert> getCustomPhoneAlertUrl(@Param(value="station") int station);
}
