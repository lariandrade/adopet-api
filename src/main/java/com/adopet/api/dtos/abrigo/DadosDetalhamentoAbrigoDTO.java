package com.adopet.api.dtos.abrigo;

import com.adopet.api.models.Abrigo;
import com.adopet.api.models.Endereco;

public record DadosDetalhamentoAbrigoDTO(Integer id, String nome, String telefone, String email, Endereco endereco) {

    public DadosDetalhamentoAbrigoDTO(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(), abrigo.getEndereco());
    }

}
