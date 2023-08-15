package com.adopet.api.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="abrigos")
@NoArgsConstructor
@Data
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;
    @OneToMany(mappedBy = "abrigo")
    private List<Pet> pets;
}
