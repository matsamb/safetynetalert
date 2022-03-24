
use prodalert;
DROP TABLE IF EXISTS  persons;
DROP TABLE IF EXISTS download;


/*create table download (
	id integer not null auto_increment primary key,
	jsondata text
	);*/

CREATE TABLE persons(
	first_name varchar(255) not null primary key,
	last_name varchar(255) not null,
	address varchar(255) not null,
	city varchar(255) not null,
	zip integer not null,
	phone varchar(255) not null,
	email varchar(255) not null
);
