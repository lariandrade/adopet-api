package com.adopet.api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuarios")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
@ToString(of = {"id", "login"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

}
