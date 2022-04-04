use prodalert;

SET GLOBAL local_infile = true;

/*insert into young (first_name, last_name, birth_date, Age, address) 
	SELECT distinct medical_records.*, 
		DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 AS Age,
		persons.address 
		from persons, medical_records 
		where DATE_FORMAT(FROM_DAYS(DATEDIFF(curdate(),STR_TO_DATE(medical_records.birth_date,'%m/%d/%Y'))), '%Y')+0 < 18 
		and persons.first_name= medical_records.first_name;*/

/*load data local infile "C:\\ProgramData\\MySQL\\MySQL server 8.0\\Uploads\\rest.json" into table download; */
