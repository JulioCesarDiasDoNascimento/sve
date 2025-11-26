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

@Named
@ViewScoped
public class UserCadastroController implements Serializable {

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

    public String cadastrar() {
        try {
            pessoaService.salvar(pessoa);
            // Limpar formulário
            init();
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar: ", e.getMessage()));
            return null;
        }
    }
}

