--bd kruger prueba
	CREATE DATABASE "pruebaKruger"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Ecuador.1252'
    LC_CTYPE = 'Spanish_Ecuador.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

	ALTER ROLE postgres IN DATABASE "pruebaKruger"
    SET client_encoding TO 'UTF8';
	
	CREATE SCHEMA IF NOT EXISTS kruger
    AUTHORIZATION postgres;
	
--creacion de tabla roles
	
	create table IF NOT EXISTS public.roles (id int primary key,descripcion varchar(100),estad0 varchar(1));
	insert into roles values(1,'Administrador','A');
	insert into roles values(2,'Empleado','A');
	
	create table IF NOT EXISTS public.usuario (id SERIAL PRIMARY KEY ,
					  cedula varchar(100),
					  nombres varchar(100),
					  apellidos varchar(100),
					  email varchar(100),
					  rol_id int,
					  CONSTRAINT roles
					  FOREIGN KEY(rol_id) 
					  REFERENCES roles(id));
					  
	insert into usuario(cedula,nombres,apellidos,email,rol_id) 
	values('123456789','administrador','administrador','administrador@gmail.com',1);
	
	CREATE TABLE IF NOT EXISTS public.infousuario
	(
		id SERIAL PRIMARY KEY,
		fechanacimiento date,
		direccion  varchar(300) ,
		telefono  varchar(50) ,
		estadovacuna  varchar(1) ,
		tipovacuna  varchar(100),
		fechavacuna date,
		dosis int,
		usuario_id int,
		cedula  varchar(10)
	);

					  