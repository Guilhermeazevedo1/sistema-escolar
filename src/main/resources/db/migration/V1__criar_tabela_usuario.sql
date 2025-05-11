CREATE TABLE usuario(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    data_nascimento DATE,
    role VARCHAR(180) NOT NULL
);