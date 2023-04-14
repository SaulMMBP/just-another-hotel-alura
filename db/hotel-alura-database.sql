# Creamos la base de datos hotel_alura
DROP SCHEMA IF EXISTS hotel_alura;
CREATE SCHEMA IF NOT EXISTS hotel_alura;

# Comenzamos a trabajar sobre la base de datos
USE hotel_alura;

# Creamos tabla usuarios
CREATE TABLE usuarios(
	id_usuario BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(300) NOT NULL,
	PRIMARY KEY(id_usuario)
);

# Creamos tabla huespedes
CREATE TABLE huespedes(
	id_huesped BIGINT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	nacionalidad VARCHAR(200) NOT NULL,
	telefono VARCHAR(100) NOT NULL,
	PRIMARY KEY(id_huesped)
);

# Creamos tabla reservas con llave foránea huesped_id
CREATE TABLE reservas(
	id_reserva BIGINT NOT NULL AUTO_INCREMENT,
	fecha_entrada TIMESTAMP NOT NULL,
	fecha_salida TIMESTAMP NOT NULL,
	valor VARCHAR(50) NOT NULL,
	forma_pago VARCHAR(100) NOT NULL,
	huesped_id BIGINT NOT NULL,
	PRIMARY KEY(id_reserva),
	FOREIGN KEY(huesped_id) REFERENCES huespedes(id_huesped)
);

# Insertamos un usuario
INSERT INTO usuarios(username, password) VALUES ('admin', '1234');

# Insertamos huespedes ejemplo
INSERT INTO `huespedes` (`nombre`,`apellido`,`fecha_nacimiento`,`nacionalidad`,`telefono`)
VALUES
  ("Kareem","Moran","2004-02-06","Afghanistan","1-555-438-1458"),
  ("Clarke","Giles","1996-11-10","Azerbaijan","1-425-759-8849"),
  ("Wyatt","Garza","1996-09-11","Cyprus","1-566-789-5888"),
  ("Curran","Hayes","2000-03-19","Finland","1-712-431-8688"),
  ("Donovan","Campos","2002-06-01","Germany","(625) 672-5735"),
  ("Alisa","Pace","1999-10-13","Germany","1-950-766-9242"),
  ("Dean","Mcgowan","1998-05-23","Germany","(443) 842-3577"),
  ("Nina","Atkinson","1999-12-14","France","1-565-753-0159"),
  ("Nichole","Maynard","2002-10-31","France","1-682-373-2681"),
  ("Lunea","Wagner","2004-03-07","Kuwait","(771) 812-7481");
  
# Insertamos reservas de ejemplo
INSERT INTO `reservas` (`fecha_entrada`,`fecha_salida`,`valor`,`forma_pago`,`huesped_id`)
VALUES
  ("2022-07-23 04:00:07","2022-08-27 20:39:25","63226.71","Tarjeta de Crédito",5),
  ("2022-05-21 22:39:24","2023-09-13 10:55:44","50961.36","Tarjeta de Débito",6),
  ("2024-01-13 09:54:52","2023-09-08 03:57:09","37299.82","Tarjeta de Crédito",7),
  ("2023-09-10 10:48:55","2024-02-17 11:07:34","43340.60","Tarjeta de Débito",2),
  ("2023-11-02 09:41:53","2024-02-26 19:21:17","62073.13","Tarjeta de Crédito",6),
  ("2023-04-21 11:02:29","2023-05-03 00:50:28","5195.05","Tarjeta de Crédito",10),
  ("2022-06-04 06:47:25","2022-10-13 18:05:18","83887.09","Tarjeta de Débito",5),
  ("2022-07-16 03:30:40","2022-04-28 15:14:14","67061.64","Dinero en efectivo",3),
  ("2023-06-28 00:11:44","2023-05-12 01:11:00","17675.81","Dinero en efectivo",8),
  ("2023-11-19 16:28:38","2023-10-09 21:23:44","34441.99","Tarjeta de Crédito",4);
  
