package com.adopet.api.models;

import com.adopet.api.dtos.pet.DadosAtualizacaoPetDTO;
import com.adopet.api.enums.PorteAnimal;

import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="pets")
@NoArgsConstructor
@Data
public class Pet extends RepresentationModel<Pet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String idade;
    private String imagem;
    @Enumerated(EnumType.STRING)
    private PorteAnimal porteAnimal;
    private String caracteristicas;
    private Boolean adotado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrigo_id")
    @JsonBackReference
    private Abrigo abrigo;

    public Pet(DadosCadastroPetDTO dados, Abrigo abrigo) {
        this.nome = dados.nome();
        this.idade = dados.idade();
        this.imagem = dados.imagem();
        this.porteAnimal =  dados.porteAnimal();
        this.caracteristicas = dados.caracteristicas();
        this.adotado = dados.adotado();
        this.abrigo = abrigo;
    }

    public void atualizarInformacoes(DadosAtualizacaoPetDTO dados) {

        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.idade = dados.idade() != null ? dados.idade() : this.idade;
        this.imagem = dados.imagem() != null ? dados.imagem() : this.imagem;
        this.caracteristicas = dados.caracteristicas() != null ? dados.caracteristicas() : this.caracteristicas;
        this.adotado = dados.adotado() != null ? dados.adotado() : this.adotado;
        this.porteAnimal = dados.porteAnimal() != null ? dados.porteAnimal() : this.porteAnimal;

    }
}
