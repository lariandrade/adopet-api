package com.adopet.api.services;

import com.adopet.api.controllers.AbrigoController;
import com.adopet.api.dtos.abrigo.DadosAtualizacaoAbrigoDTO;
import com.adopet.api.dtos.abrigo.DadosCadastroAbrigoDTO;
import com.adopet.api.dtos.abrigo.DadosDetalhamentoAbrigoDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Abrigo;
import com.adopet.api.repositories.AbrigoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }


    private Abrigo getAbrigoById(Integer id) {
        return abrigoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Nenhum abrigo foi encontrado com esse id."));
    }

    public DadosDetalhamentoAbrigoDTO save(DadosCadastroAbrigoDTO dados) {

        Abrigo novoAbrigo = new Abrigo(dados);
        abrigoRepository.save(novoAbrigo);
        return new DadosDetalhamentoAbrigoDTO(novoAbrigo);
    }

    public DadosDetalhamentoAbrigoDTO update(Integer id, DadosAtualizacaoAbrigoDTO dados) {
        var abrigo = getAbrigoById(id);
        abrigo.atualizarInformacoes(dados);
        return new DadosDetalhamentoAbrigoDTO(abrigo);
    }

    public PagedModel<EntityModel<Abrigo>> getAllAbrigos(Pageable pageable, PagedResourcesAssembler<Abrigo> assembler) {
        Page<Abrigo> abrigosPage = abrigoRepository.findAll(pageable);
        if (abrigosPage.isEmpty()) {
            throw new ValidacaoException("Nenhum abrigo encontrado");
        }
        return assembler.toModel(abrigosPage, abrigo -> {
            Integer id = abrigo.getId();
            return EntityModel.of(abrigo, linkTo(methodOn(AbrigoController.class).listarUm(id)).withSelfRel());
        });
    }

    public Abrigo getOneAbrigo(Integer id) {
        var abrigo = getAbrigoById(id);
        abrigo.add(linkTo(methodOn(AbrigoController.class).listarTodos(null)).withRel("Lista de abrigos"));
        return abrigo;
    }

    public void delete(Integer id) {
        Abrigo abrigo = getAbrigoById(id);
        abrigoRepository.delete(abrigo);
    }
}
