package com.adopet.api.dtos.adocao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroAdocaoDTO(@NotNull Integer petId,
                                     @NotNull Integer tutorId,
                                     @NotNull LocalDate data) {
}
