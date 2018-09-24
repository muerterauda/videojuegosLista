DROP DATABASE IF EXISTS videojuegos;

CREATE DATABASE videojuegos;

CREATE USER IF NOT EXISTS 'Lara'@'localhost' IDENTIFIED WITH mysql_native_password BY 'tomb';

GRANT ALL ON videojuegos.* TO 'Lara'@'localhost';

USE videojuegos;

CREATE TABLE LANGUAGE(
	id BIGINT SIGNED NOT NUll auto_increment,
    locale varchar(2) NOT NULL,
    extendLocal varchar(5) NOT NULL,
    name varchar(30) NOT NULL,
    srcImage varchar(50),
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE RATING(
	id BIGINT SIGNED NOT NUll auto_increment,
    rating varchar(4) NOT NULL unique,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE VIDEOJUEGO (
	id BIGINT SIGNED NOT NUll auto_increment,
    name VARCHAR(50) NOT NULL,
    company VARCHAR(50),
    rating BIGINT SIGNED,
    FOREIGN KEY(rating) REFERENCES RATING(id),
    PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE table USUARIO (
	id  BIGINT SIGNED NOT NUll auto_increment,
    nombre varchar(50) NOT NULL,
    contrasenya varchar(50)  NOT NULL, 
    enabled BOOLEAN  NOT NULL,
    role varchar(5) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB;

INSERT INTO LANGUAGE(locale, extendLocal, name, srcImage) values
('es', 'es-ES', 'Español', 'images/es.png'),('en', 'en-GB', 'English', 'images/en.png'),('pt', 'pt-BR', 'Português', 'images/pt.png');

INSERT INTO USUARIO(nombre, contrasenya, enabled, role) VALUES
('user','user',true,'USER'),('admin','admin',true,'ADMIN');

INSERT INTO RATING(rating) VALUES
('+3'),
('+7'),
('+12'),
('+16'),
('+18');

INSERT INTO VIDEOJUEGO(name,company,rating) VALUES
('Half Life 3', 'Valve', 1),
('Minecraft', 'Mojang', 5),
('World of Warcraft', 'Blizzard', 5),
('League of Leguends', 'Riot Games', 3),
('Dark Souls', 'From Software', 5),
('Shadow of the Tomb Raider', 'Core Design', 4);
    
