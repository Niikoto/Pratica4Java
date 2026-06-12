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
	cod_cliente INT null,
	cod_raca INT null,
	status_animal TINYINT,
	CONSTRAINT cod_cliente
		FOREIGN kEY (cod_cliente) 
		REFERENCES cliente(id)
        ON DELETE SET NULL,
	CONSTRAINT cod_raca
		FOREIGN key (cod_raca)
		REFERENCES raca(id_raca)
        ON DELETE SET NULL
);

INSERT INTO cliente (nome, cpf, data_nascimento, telefone, endereco, bairro, cidade, estado, cep)
VALUES
('João Silva', '12345678901', '1990-05-15', '12999998888', 'Rua A, 100', 'Centro', 'São José dos Campos', 'SP', '12200000'),
('Maria Souza', '98765432100', '1985-10-20', '12988887777', 'Rua B, 200', 'Jardim', 'Taubaté', 'SP', '12000000');

INSERT INTO raca (nome_raca, tipo_animal, status_raca)
VALUES
('Labrador', 'Cachorro', 1),
('Persa', 'Gato', 1);

INSERT INTO animal (nome_animal, data_nascimento, sexo, cor, observacoes, cod_cliente, cod_raca, status_animal)
VALUES
('Rex', '2022-08-10', 'M', 'Caramelo', 'Muito brincalhão', 1, 1, 1),
('Mimi', '2023-01-05', 'F', 'Branca', 'Gosta de dormir', 2, 2, 1);

select * from animal;