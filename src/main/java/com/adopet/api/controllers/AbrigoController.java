package com.adopet.api.controllers;

import com.adopet.api.dtos.abrigo.DadosAtualizacaoAbrigoDTO;
import com.adopet.api.dtos.abrigo.DadosCadastroAbrigoDTO;
import com.adopet.api.dtos.abrigo.DadosDetalhamentoAbrigoDTO;
import com.adopet.api.models.Abrigo;
import com.adopet.api.services.AbrigoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoService abrigoService;
    private final PagedResourcesAssembler<Abrigo> assembler;

    public AbrigoController(AbrigoService abrigoService, PagedResourcesAssembler<Abrigo> assembler) {
        this.abrigoService = abrigoService;
        this.assembler = assembler;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAbrigoDTO> cadastrar(@RequestBody @Valid DadosCadastroAbrigoDTO dados) {
        DadosDetalhamentoAbrigoDTO dadosDetalhamento = abrigoService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Abrigo>>> listarTodos(@PageableDefault(sort = {"id"}) Pageable pageable) {
        PagedModel<EntityModel<Abrigo>> pagedModel = abrigoService.getAllAbrigos(pageable, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abrigo> listarUm(@PathVariable Integer id) {
        var abrigo =  abrigoService.getOneAbrigo(id);
        return ResponseEntity.status(HttpStatus.OK).body(abrigo);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAbrigoDTO> atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoAbrigoDTO dados) {
        DadosDetalhamentoAbrigoDTO abrigoAtualizado = abrigoService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(abrigoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        abrigoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
