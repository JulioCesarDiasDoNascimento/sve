-- Enums

CREATE TYPE sexo AS ENUM ('MASCULINO', 'FEMININO', 'OUTROS');
CREATE TYPE partidos AS ENUM ('PT','PL','UNIAO','PP','MDB','PSD','REPUBLICANOS','PSDB','PSB','PDT','MBL');

-- Tabelas

CREATE TABLE enderecos (
    id SERIAL PRIMARY KEY,
    logradouro varchar(255),
    numero varchar(50),
    complemento TEXT,
    bairro varchar(255),
    cidade varchar(255),
    uf VARCHAR(2),
    cep VARCHAR(20)
);

CREATE TABLE pessoas (
    id SERIAL PRIMARY KEY,
    nome varchar(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    rg VARCHAR(50),
    cnh VARCHAR(50),
    data_nascimento DATE,
    endereco_id INTEGER REFERENCES enderecos(id) ON DELETE SET NULL,
    telefone VARCHAR(50),
    email VARCHAR(255),
    senha varchar(255),
    idade INTEGER,
    sexo sexo
);

CREATE TABLE eleitores (
    id INTEGER PRIMARY KEY REFERENCES pessoas(id) ON DELETE CASCADE,
    titulo_eleitor VARCHAR(100),
    zona_eleitoral VARCHAR(50),
    secao_eleitoral VARCHAR(50),
    votou BOOLEAN DEFAULT FALSE
);

CREATE TABLE candidatos (
    id INTEGER PRIMARY KEY REFERENCES pessoas(id) ON DELETE CASCADE,
    partido partidos,
    estado VARCHAR(2),
    candidatura VARCHAR(100),
    votos INTEGER DEFAULT 0,
    data_voto TIMESTAMP,
    numero VARCHAR(10)
);

CREATE TABLE pontos_de_voto (
    id SERIAL PRIMARY KEY,
    nome_do_ponto varchar(255),
    endereco_id INTEGER REFERENCES enderecos(id) ON DELETE SET NULL,
    data DATE,
    hora_inicio TIMESTAMP,
    hora_fim TIMESTAMP,
    fuso_horario varchar(255)
);

-- Índices básicos
CREATE INDEX idx_pessoas_email ON pessoas(email);
CREATE INDEX idx_candidatos_partido ON candidatos(partido);
