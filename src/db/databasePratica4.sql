create database Pratica4;

use Pratica4;

CREATE TABLE cliente (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(30),
	cpf VARCHAR(11),
	data_nascimento DATE,
	telefone VARCHAR(20),
	endereco VARCHAR(150),
	bairro VARCHAR(100),
	cidade VARCHAR(100),
	estado VARCHAR(2),
	cep VARCHAR(10)
);

CREATE TABLE raca (
	id_raca int AUTO_INCREMENT primary key,
	nome_raca VARCHAR(30) Not null,
	tipo_animal VARCHAR(30),
	status_raca TINYINT
);

CREATE TABLE animal(
	id_animal int AUTO_INCREMENT primary key,
	nome_animal VARCHAR(30) not null,
	data_nascimento DATE,
	sexo VARCHAR(1),
	cor VARCHAR(30),
	observacoes VARCHAR(50),
	cod_cliente INT not null,
	cod_raca INT not null,
	status_animal TINYINT,
	CONSTRAINT cod_cliente
		FOREIGN kEY (cod_cliente) 
		REFERENCES cliente(id),
	CONSTRAINT cod_raca
		FOREIGN key (cod_raca)
		REFERENCES raca(id_raca)
);