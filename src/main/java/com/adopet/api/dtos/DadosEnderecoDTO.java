package com.adopet.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosEnderecoDTO(@NotBlank
                               String cidade,
                               @NotBlank
                               String UF) {
}
