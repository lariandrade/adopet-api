package com.adopet.api.dtos.pet;

import com.adopet.api.enums.PorteAnimal;
import com.adopet.api.models.Pet;

public record DadosDetalhamentoPetDTO(Integer id,
                                      String nome,
                                      String idade,
                                      String imagem,
                                      PorteAnimal porteAnimal,
                                      String caracteristicas,
                                      Integer abrigo_id,
                                      Boolean adotado) {
    public DadosDetalhamentoPetDTO(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getIdade(), pet.getImagem(), pet.getPorteAnimal(), pet.getCaracteristicas(), pet.getAbrigo().getId(), pet.getAdotado());
    }
}
