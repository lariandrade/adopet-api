CREATE TABLE abrigos (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(50),
    endereco_cidade VARCHAR(30),
    endereco_uf VARCHAR(2),
    PRIMARY KEY(id)
);
