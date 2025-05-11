CREATE TABLE aluno(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE
);