/*drop database prodalert;
create database prodalert;*/
use prodalert;

/*DROP TABLE IF EXISTS  young;
create table young (
	first_name varchar(50),
	last_name varchar(50),
	birth_date varchar (50),
	Age integer,
	address varchar(100),
	primary key (first_name, last_name)
	) ;*/

/*DROP TABLE IF EXISTS  persons;

CREATE TABLE persons(
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	address varchar(255) not null,
	city varchar(255) not null,
	zip integer not null,
	phone varchar(255) not null,
	email varchar(255) not null,
	primary key (first_name,last_name)
);

create table medical_records(
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	birth_date varchar(255),
	primary key (first_name,last_name)
);

create table medications(
	medication varchar(255) not null primary key
	);

create table allergies(
	allergy varchar(255) not null primary key,
	);

create table medical_records_medications(
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	medication varchar(255) not null,
	medications_order integer,
	foreign key (medical_records_first_name,medical_records_first_name) 
		references medical_records(first_name,last_name)
		ON UPDATE CASCADE ON DELETE RESTRICT
);

create table medical_records_allergies(
	first_name varchar(255) not null ,
	last_name varchar(255) not null,
	allergy varchar(255) not null,
	allergies_order integer,
	foreign key (medical_records_first_name,medical_records_first_name) 
		references medical_records(first_name,last_name)
		ON UPDATE CASCADE ON DELETE RESTRICT
);
*/