CREATE DATABASE sivbd;
USE sivbd;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    contrasenia VARCHAR(255),
    administrador BOOLEAN DEFAULT FALSE
);

INSERT INTO usuario (nombre, contrasenia, administrador)
VALUES
    ('admin', 'root', TRUE),
    ('guille', '1234', FALSE),
    ('luchi', 'dg1234', FALSE),
    ('mario', 'abcd', TRUE);

select * from usuario;
describe usuario;

SELECT administrador FROM usuario WHERE nombre = 'guille';

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    existencia FLOAT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    precio FLOAT NOT NULL
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechayhora DATETIME DEFAULT NULL,
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE producto_vendido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad FLOAT DEFAULT NULL,
    codigo VARCHAR(255) DEFAULT NULL,
    nombre VARCHAR(255) DEFAULT NULL,
    precio FLOAT DEFAULT NULL,
    venta_id INT,
    FOREIGN KEY (venta_id) REFERENCES venta(id)
);
