# Heliant - Sprint zadatak

- Kreiranje baze podataka

	CREATE DATABASE heliant;

- Kreiranje user-a

	CREATE USER 'heliant_user'@'localhost' IDENTIFIED BY 'heliant_password';

- Dodeljivanje privilegija user-u

	GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, INDEX, DROP, ALTER, CREATE TEMPORARY TABLES, 	LOCK TABLES, REFERENCES ON heliant.* TO 'heliant_user'@'localhost';
	
- Swagger dokumentacija

	http://localhost:8080/swagger-ui/index.html
