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