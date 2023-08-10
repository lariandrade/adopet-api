package com.adopet.api.controllers;

import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.services.TutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTutorDTO> cadastrar(@RequestBody @Valid DadosCadastroTutorDTO dados) {
        DadosDetalhamentoTutorDTO dadosDetalhamento = tutorService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);
    }
}
