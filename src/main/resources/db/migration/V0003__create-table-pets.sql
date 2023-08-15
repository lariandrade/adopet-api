CREATE TABLE pets (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    idade  VARCHAR(10) NOT NULL,
    porteAnimal VARCHAR(13) NOT NULL,
    caracteristicas VARCHAR(100) NOT NULL,
    adotado TINYINT(1) NOT NULL,
    abrigo_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(abrigo_id) REFERENCES abrigos(id)
);

