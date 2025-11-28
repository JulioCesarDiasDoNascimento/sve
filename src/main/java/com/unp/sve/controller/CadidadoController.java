package com.unp.sve.controller;

import com.unp.sve.domain.Candidato;
import com.unp.sve.domain.Endereco;
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
public class CadidadoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Candidato candidado;

    @Autowired
    private PessoaService pessoaService;

    public java.util.List<com.unp.sve.domain.Candidato> getLista() {
        return pessoaService.listarCandidatos();
    }

    @PostConstruct
    public void init() {
        candidado = new Candidato();
        candidado.setEndereco(new Endereco());
    }

    public String criarNovo() {
        try {
            return "/cadastros/cadastrar_candidato.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar: ", e.getMessage()));
            return null;
        }

    }

    public String registrarCandidato() {
        try {
            if (candidado.getCpf() != null && !candidado.getCpf().isBlank()) {
                pessoaService.buscarPorCpf(candidado.getCpf()).ifPresent(p -> candidado.setId(p.getId()));
            }
            pessoaService.atualizar(candidado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Candidato cadastrado/atualizado com sucesso", null));
            return "/cadastros/gerenciamento-candidato.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar candidato:", e.getMessage()));
            return null;
        }
    }

    public void buscarPorCpf() {
        try {
            if (candidado != null && candidado.getCpf() != null && !candidado.getCpf().isBlank()) {
                pessoaService.buscarPorCpf(candidado.getCpf()).ifPresent(p -> {
                    candidado.setNome(p.getNome());
                    candidado.setTelefone(p.getTelefone());
                    candidado.setEmail(p.getEmail());
                    if (p instanceof Candidato) {
                        Candidato c = (Candidato) p;
                        candidado.setNumero(c.getNumero());
                        candidado.setPartido(c.getPartido());
                        candidado.setPropostas(c.getPropostas());
                    }
                });
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar CPF:", e.getMessage()));
        }
    }
}
