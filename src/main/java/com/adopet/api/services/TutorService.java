package com.adopet.api.services;

import com.adopet.api.dtos.DadosAtualizacaoTutorDTO;
import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Tutor;
import com.adopet.api.repositories.TutorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        tutorRepository.save(novoTutor);
        return new DadosDetalhamentoTutorDTO(novoTutor);
    }

    public DadosDetalhamentoTutorDTO update(Integer id, DadosAtualizacaoTutorDTO dados) {
        var tutor = getTutorById(id);
        tutor.atualizarInformacoes(dados);
        return new DadosDetalhamentoTutorDTO(tutor);
    }

    private Tutor getTutorById(Integer id) {
        return tutorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Nenhum tutor foi encontrado com esse id."));
    }
}
