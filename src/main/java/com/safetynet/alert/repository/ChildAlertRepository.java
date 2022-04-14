package com.safetynet.alert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.safetynet.alert.entity.ChildAlert;

public interface ChildAlertRepository extends JpaRepository<ChildAlert, String>{

	@Query(value="  SELECT *, ROW_NUMBER() OVER(ORDER BY age ASC) AS id  FROM young where young.address = :address UNION SELECT *, ROW_NUMBER() OVER(ORDER BY age ASC) AS id  FROM(SELECT distinct medical_records.*, DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age, persons.address from persons, medical_records, young where if(young.address = :address and young.address=persons.address,1,0) = 1 and medical_records.first_name = persons.first_name) as a where a.age > 18 ;",nativeQuery=true)  
	Iterable<ChildAlert> getCustomChildAlertUrl(@Param(value="address") String address);
	
}
