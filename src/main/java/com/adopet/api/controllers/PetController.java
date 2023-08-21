package com.adopet.api.controllers;

import com.adopet.api.dtos.pet.DadosAtualizacaoPetDTO;
import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import com.adopet.api.dtos.pet.DadosDetalhamentoPetDTO;
import com.adopet.api.models.Pet;
import com.adopet.api.services.PetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final PagedResourcesAssembler<Pet> assembler;

    public PetController(PetService petService, PagedResourcesAssembler<Pet> assembler) {
        this.petService = petService;
        this.assembler = assembler;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPetDTO> cadastrar(@RequestBody @Valid DadosCadastroPetDTO dados) {
        DadosDetalhamentoPetDTO dadosDetalhamento = petService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Pet>>> listarTodos(@PageableDefault(sort = {"id"}) Pageable pageable) {
        PagedModel<EntityModel<Pet>> pagedModel = petService.getAllPets(pageable, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> listarUm(@PathVariable Integer id) {
        var pet =  petService.getOnePet(id);
        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }

    @PutMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity<DadosDetalhamentoPetDTO> atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoPetDTO dados) {
        DadosDetalhamentoPetDTO petAtualizado = petService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(petAtualizado);
    }

}
