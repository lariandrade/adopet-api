package com.adopet.api.controllers;

import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import com.adopet.api.dtos.pet.DadosDetalhamentoPetDTO;
import com.adopet.api.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPetDTO> cadastrar(@RequestBody @Valid DadosCadastroPetDTO dados) {
        DadosDetalhamentoPetDTO dadosDetalhamento = petService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);

    }

}
