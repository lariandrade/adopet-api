package com.adopet.api.services;

import com.adopet.api.controllers.PetController;
import com.adopet.api.dtos.pet.DadosAtualizacaoPetDTO;
import com.adopet.api.dtos.pet.DadosDetalhamentoPetDTO;
import com.adopet.api.exceptions.ValidacaoException;
import com.adopet.api.models.Abrigo;
import com.adopet.api.models.Pet;
import com.adopet.api.repositories.AbrigoRepository;
import com.adopet.api.repositories.PetRepository;
import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final AbrigoRepository abrigoRepository;

    public PetService(PetRepository petRepository, AbrigoRepository abrigoRepository) {
        this.petRepository = petRepository;
        this.abrigoRepository = abrigoRepository;
    }

    private Pet getPetById(Integer id) {
        return petRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Nenhum pet foi encontrado com esse id."));
    }

    public DadosDetalhamentoPetDTO save(DadosCadastroPetDTO dados) {

        Optional<Abrigo> optionalAbrigo = abrigoRepository.findById(dados.abrigo_id());
        if (optionalAbrigo.isEmpty()) {
             throw new ValidacaoException("Abrigo não encontrado.");
        }

        Abrigo abrigo = optionalAbrigo.get();

        Pet novoPet = new Pet(dados, abrigo);
        petRepository.save(novoPet);
        return new DadosDetalhamentoPetDTO(novoPet);

    }

    public PagedModel<EntityModel<Pet>> getAllPets(Pageable pageable, PagedResourcesAssembler<Pet> assembler) {
        Page<Pet> petsPage = petRepository.findAll(pageable);
        if (petsPage.isEmpty()) {
            throw new ValidacaoException("Nenhum pet encontrado");
        }
        return assembler.toModel(petsPage, pet -> {
            Integer id = pet.getId();
            return EntityModel.of(pet, linkTo(methodOn(PetController.class).listarUm(id)).withSelfRel());
        });
    }

    public Pet getOnePet(Integer id) {
        var pet = getPetById(id);
        pet.add(linkTo(methodOn(PetController.class).listarTodos(null)).withRel("Lista de pets"));
        return pet;
    }

    public DadosDetalhamentoPetDTO update(Integer id, DadosAtualizacaoPetDTO dados) {
        var pet = getPetById(id);
        pet.atualizarInformacoes(dados);
        return new DadosDetalhamentoPetDTO(pet);
    }

    public void delete(Integer id) {
        Pet pet = getPetById(id);
        petRepository.delete(pet);
    }
}
