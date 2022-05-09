CREATE TABLE crypto (
	id int NOT NULL AUTO_INCREMENT,
	name varchar (30) NOT NULL, 
	acronym varchar (4) NOT NULL UNIQUE,
	value int, 
	enabled boolean, 
	PRIMARY KEY (id)
	);

