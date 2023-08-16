package com.adopet.api.dtos.tutor;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTutorDTO(@NotBlank String nome,
                                    @NotBlank String email,
                                    @NotBlank String senha,
                                    @NotBlank String confirmacaoSenha) {
}
