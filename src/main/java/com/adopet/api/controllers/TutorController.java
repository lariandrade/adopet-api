package com.adopet.api.controllers;

import com.adopet.api.dtos.DadosAtualizacaoTutorDTO;
import com.adopet.api.dtos.DadosCadastroTutorDTO;
import com.adopet.api.dtos.DadosDetalhamentoTutorDTO;
import com.adopet.api.models.Tutor;
import com.adopet.api.services.TutorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;
    private final PagedResourcesAssembler<Tutor> assembler;

    public TutorController(TutorService tutorService, PagedResourcesAssembler<Tutor> assembler) {
        this.tutorService = tutorService;
        this.assembler = assembler;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTutorDTO> cadastrar(@RequestBody @Valid DadosCadastroTutorDTO dados) {
        DadosDetalhamentoTutorDTO dadosDetalhamento = tutorService.save(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Tutor>>> listarTodos(@PageableDefault(sort = {"id"}) Pageable pageable) {
        PagedModel<EntityModel<Tutor>> pagedModel = tutorService.getAllTutores(pageable, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> listarUm(@PathVariable Integer id) {
       var resl =  tutorService.getOneTutor(id);
       return ResponseEntity.status(HttpStatus.OK).body(resl);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTutorDTO> atualizar(@PathVariable Integer id, @RequestBody DadosAtualizacaoTutorDTO dados) {
        DadosDetalhamentoTutorDTO tutorAtualizado = tutorService.update(id, dados);
        return ResponseEntity.status(HttpStatus.OK).body(tutorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        tutorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
