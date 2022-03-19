use prodalert;
DROP TABLE IF EXISTS  persons;
DROP TABLE IF EXISTS download;

create table download (
	id integer not null auto_increment primary key,
	jsondata text
	);
/*CREATE TABLE persons();*/