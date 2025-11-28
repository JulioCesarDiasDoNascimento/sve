package com.unp.sve.controller;

import com.unp.sve.domain.Candidato;
import com.unp.sve.domain.Endereco;
import com.unp.sve.domain.Pessoa;
import com.unp.sve.service.PessoaService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Named
@ViewScoped
public class CadidadoController {

    @Getter
    @Setter
    private Candidato candidado;

    @Autowired
    private PessoaService pessoaService;


    @PostConstruct
    public void init() {
        candidado = new Candidato();
        candidado.setEndereco(new Endereco());
    }

    public String criarNovo() {
        return "cadastros/cadastrar_candidato.xhtml?faces-redirect=true";
    }
}
