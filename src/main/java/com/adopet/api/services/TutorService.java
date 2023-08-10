package com.adopet.api.services;

import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Tutor;
import com.adopet.api.repositories.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public DadosDetalhamentoTutorDTO save(DadosCadastroTutorDTO dados) {

        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            throw new ValidacaoException("Senhas não conferem!");
        }

        Tutor novoTutor = new Tutor(dados);

        return new DadosDetalhamentoTutorDTO(novoTutor);
    }
}
