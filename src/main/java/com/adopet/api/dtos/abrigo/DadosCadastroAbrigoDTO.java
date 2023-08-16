package com.adopet.api.dtos.abrigo;

import com.adopet.api.dtos.DadosEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAbrigoDTO(@NotBlank String nome,
                                     @NotBlank String telefone,
                                     @NotBlank @Email String email,
                                     @NotNull
                                     @Valid
                                     DadosEnderecoDTO endereco) {
}
