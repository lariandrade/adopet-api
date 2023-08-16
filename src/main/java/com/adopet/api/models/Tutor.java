package com.adopet.api.models;

import com.adopet.api.dtos.tutor.DadosAtualizacaoTutorDTO;
import com.adopet.api.dtos.tutor.DadosCadastroTutorDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="tutores")
@NoArgsConstructor
@Data
public class Tutor extends RepresentationModel<Tutor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    public Tutor(DadosCadastroTutorDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void atualizarInformacoes(DadosAtualizacaoTutorDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null){
            this.email = dados.email();
        }

        if (dados.senha() != null){
            this.senha = dados.senha();
        }
    }
}
