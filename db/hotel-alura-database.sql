# Creamos la base de datos hotel_alura
CREATE SCHEMA IF NOT EXISTS hotel_alura;

# Comenzamos a trabajar sobre la base de datos
USE hotel_alura;

# Creamos tabla usuarios
CREATE TABLE usuarios(
	id BIGINT NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(300) NOT NULL,
	PRIMARY KEY(id)
);

# Creamos tabla huespedes
CREATE TABLE huespedes(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(100) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	nacionalidad VARCHAR(200) NOT NULL,
	telefono VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
);

# Creamos tabla reservas con llave foránea huesped_id
CREATE TABLE reservas(
	id BIGINT NOT NULL AUTO_INCREMENT,
	fecha_entrada DATETIME NOT NULL,
	fecha_salida DATETIME,
	valor DECIMAL(10,2),
	forma_pago VARCHAR(100) NOT NULL,
	huesped_id BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(huesped_id) REFERENCES huespedes(id)
);

# Insertamos un usuario
INSERT INTO usuarios(username, password) VALUES ('admin', '1234');
