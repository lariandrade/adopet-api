package com.adopet.api.models;

import com.adopet.api.enums.PorteAnimal;

import com.adopet.api.dtos.pet.DadosCadastroPetDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pets")
@NoArgsConstructor
@Data
public class Pet {
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
}
