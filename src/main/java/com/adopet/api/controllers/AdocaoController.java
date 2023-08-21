package com.adopet.api.controllers;

import com.adopet.api.dtos.adocao.DadosCadastroAdocaoDTO;
import com.adopet.api.models.Adocao;
import com.adopet.api.services.AdocaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Adocao> cadastrar(@RequestBody @Valid DadosCadastroAdocaoDTO dados) {
        var adocao = adocaoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(adocao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        adocaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
