package com.adopet.api.models;

import com.adopet.api.dtos.adocao.DadosCadastroAdocaoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="adocoes")
@NoArgsConstructor
@Data
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Adocao(DadosCadastroAdocaoDTO dados, Pet pet, Tutor tutor) {
        this.data = dados.data();
        this.pet = pet;
        this.tutor = tutor;
    }
}
