package com.adopet.api.enums;

public enum PorteAnimal {
    PEQUENO("Porte pequeno"),
    MEDIO("Porte médio"),
    GRANDE("Porte grande");

    private final String descricao;

    PorteAnimal(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
