package com.adopet.api.dtos.tutor;

import com.adopet.api.models.Tutor;

public record DadosDetalhamentoTutorDTO(Integer id, String nome, String email, String senha) {

    public DadosDetalhamentoTutorDTO(Tutor novoTutor) {
        this(novoTutor.getId(), novoTutor.getNome(), novoTutor.getEmail(), novoTutor.getSenha());
    }
}
