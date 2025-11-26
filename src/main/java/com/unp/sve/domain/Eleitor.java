package com.unp.sve.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eleitor extends Pessoa{

    // Classe para os eleitores
    private String tituloEleitor;
    private String zonaEleitoral;
    private String secaoEleitoral;
    private boolean votou;
}
