package com.unp.sve.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String cnh;

    private LocalDate dataNascimento;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String telefone;
    private String email;
    private String senha;
    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
}
