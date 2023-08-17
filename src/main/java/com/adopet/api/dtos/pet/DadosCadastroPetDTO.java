package com.adopet.api.dtos.pet;

import com.adopet.api.enums.PorteAnimal;
import com.adopet.api.models.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPetDTO(@NotBlank String nome,
                                  @NotBlank String idade,
                                  @NotBlank String imagem,
                                  @NotNull PorteAnimal porteAnimal,
                                  @NotBlank String caracteristicas,
                                  @NotNull Integer abrigo_id,
                                  @NotNull Boolean adotado) {
}
