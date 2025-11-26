package com.unp.sve.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Candidato extends Pessoa{

    // variáveis para os candidatos politicos
    private Partidos partido;
    private String estado;
    private String candidatura;
    private int votos;
    private Date dataVoto;
    private String numero;
}
