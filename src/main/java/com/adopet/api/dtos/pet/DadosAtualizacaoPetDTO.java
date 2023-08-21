package com.adopet.api.dtos.pet;

import com.adopet.api.enums.PorteAnimal;

public record DadosAtualizacaoPetDTO(String nome, String idade, String imagem, PorteAnimal porteAnimal, String caracteristicas, Boolean adotado) {
}
