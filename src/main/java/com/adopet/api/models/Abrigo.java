package com.adopet.api.models;

import com.adopet.api.dtos.abrigo.DadosAtualizacaoAbrigoDTO;
import com.adopet.api.dtos.abrigo.DadosCadastroAbrigoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
@Table(name="abrigos")
@NoArgsConstructor
@Data
public class Abrigo extends RepresentationModel<Abrigo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "abrigo", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pet> pets;

    public Abrigo(DadosCadastroAbrigoDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoAbrigoDTO dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.email() != null ? dados.email() : this.email;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;

        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
