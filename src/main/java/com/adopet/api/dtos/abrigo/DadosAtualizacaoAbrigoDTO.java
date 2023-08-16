package com.adopet.api.dtos.abrigo;

import com.adopet.api.dtos.DadosEnderecoDTO;

public record DadosAtualizacaoAbrigoDTO(String nome, String telefone, String email, DadosEnderecoDTO endereco) {
}
