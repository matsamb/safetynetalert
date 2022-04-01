package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.CommunityEmail;

public interface CommunityEmailRepository extends JpaRepository<CommunityEmail, String>{

	@Query(value="select distinct persons.email, persons.city from persons where persons.city = 'culver' order by persons.email asc ;",nativeQuery = true)
	Iterable<CommunityEmail> getCustomCommunityEmailUrl(@Param(value="city") String city);
}
