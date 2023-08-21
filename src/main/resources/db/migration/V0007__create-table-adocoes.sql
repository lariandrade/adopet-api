CREATE TABLE adocoes(

    id INT NOT NULL auto_increment,
    pet_id INT NOT NULL,
    tutor_id INT NOT NULL,
    data DATE NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(pet_id) REFERENCES pets(id),
    FOREIGN KEY(tutor_id) REFERENCES tutores(id)

);