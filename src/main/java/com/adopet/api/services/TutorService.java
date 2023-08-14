package com.adopet.api.services;

import com.adopet.api.controllers.TutorController;
import com.adopet.api.dtos.DadosAtualizacaoTutorDTO;
import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Tutor;
import com.adopet.api.repositories.TutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    public PagedModel<EntityModel<Tutor>> getAllTutores(Pageable pageable, PagedResourcesAssembler<Tutor> assembler) {
        Page<Tutor> tutoresPage = tutorRepository.findAll(pageable);
        return assembler.toModel(tutoresPage, tutor -> {
            Integer id = tutor.getId();
            return EntityModel.of(tutor, linkTo(methodOn(TutorController.class).listarUm(id)).withSelfRel());
        });
    }

    public Tutor getOneTutor(Integer id) {
        var tutor = getTutorById(id);

        tutor.add(linkTo(methodOn(TutorController.class).listarTodos(null)).withRel("Lista de tutores"));

        return tutor;
    }

    private Tutor getTutorById(Integer id) {
        return tutorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Nenhum tutor foi encontrado com esse id."));
    }
}
