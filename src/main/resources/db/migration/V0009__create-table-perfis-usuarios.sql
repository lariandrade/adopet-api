CREATE TABLE perfis(

    id INT NOT NULL auto_increment,
    nome VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)

);

CREATE TABLE usuarios_perfis(

    usuario_id INT NOT NULL,
    perfil_id INT NOT NULL,

    FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY(perfil_id) REFERENCES perfis(id),
    PRIMARY KEY(usuario_id, perfil_id)

);

INSERT INTO perfis VALUES(1, 'ROLE_TUTOR');
INSERT INTO perfis VALUES(2, 'ROLE_ABRIGO');