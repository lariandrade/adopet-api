package com.adopet.api.controllers;

import com.adopet.api.dtos.DadosAtualizacaoTutorDTO;
import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.services.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTutorDTO> cadastrar(@RequestBody @Valid DadosCadastroTutorDTO dados) {
        DadosDetalhamentoTutorDTO dadosDetalhamento = tutorService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTutorDTO> atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoTutorDTO dados) {
        DadosDetalhamentoTutorDTO tutorAtualizado = tutorService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(tutorAtualizado);
    }

}
