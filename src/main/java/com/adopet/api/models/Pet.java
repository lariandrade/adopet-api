package com.adopet.api.models;

import com.adopet.api.enums.PorteAnimal;

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
    @Enumerated(EnumType.STRING)
    private PorteAnimal porteAnimal;
    private String caracteristicas;
    private Boolean adotado;
    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

}
