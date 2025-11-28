package com.unp.sve.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "candidatos")
public class Candidato extends Pessoa{

    // variáveis para os candidatos politicos
    @Enumerated(EnumType.STRING)
    private Partidos partido;

    private String estado;
    private String candidatura;

    private int votos = 0;

    private Date dataVoto;

    @Column(unique = true)
    private int numero;
}
