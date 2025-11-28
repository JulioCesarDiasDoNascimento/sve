package com.unp.sve.controller;

import com.unp.sve.domain.Pessoa;
import com.unp.sve.service.PessoaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EleitorController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaService pessoaService;

    public List<Pessoa> getLista() {
        return pessoaService.listarTodas();
    }

    public String criarNovo() {
        try {
            return "/cadastros/novo_usuario.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar: ", e.getMessage()));
            return null;
        }

    }
}
