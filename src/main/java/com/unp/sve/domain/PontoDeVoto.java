package com.unp.sve.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PontoDeVoto {

    private String nomeDoPonto;
    private Endereco endereco;
    private Date data;
    private Date horaIncio;
    private Date horaFim;
    private Date fusoHorario;
}
