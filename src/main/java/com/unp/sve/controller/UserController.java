package com.unp.sve.controller;

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

import java.io.Serializable;
import java.util.Optional;

@Named
@ViewScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Pessoa pessoa;

    @Autowired
    private PessoaService pessoaService;

    @PostConstruct
    public void init() {
        pessoa = new Pessoa();
        pessoa.setEndereco(new Endereco());
    }

    public String login() {
        try {
            Optional<Pessoa> user = pessoaService.validarLogin(pessoa.getCpf(), pessoa.getSenha());
            if (user.isPresent()) {
                return "/index.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF/Senha inválidos", null));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no login: ", e.getMessage()));
            return null;
        }
    }
}

