package com.unp.sve.controller;

import com.unp.sve.domain.Candidato;
import com.unp.sve.domain.Endereco;
import com.unp.sve.domain.Partidos;
import com.unp.sve.domain.Pessoa;
import com.unp.sve.service.VotacaoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Console;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class VotacaoController implements Serializable {

    @Getter
    @Setter
    private Candidato candidato;

    public void init() {
        candidato = new Candidato();
    }

    private String numeroDigitado = "";
    //precisa ser string, se for int nao consigo digitar 05, fica 5(quando digita pelos numeros da interfaces)

    @Autowired
    private VotacaoService votacaoService;

    public void adicionarNumero(int numero) {
        if (numeroDigitado.length() < 2) {
            numeroDigitado += numero;
        }
    }

    public void votoBranco() {
        numeroDigitado = "BRANCO";
    }

    public void corrigir() {
        numeroDigitado = "";
    }

    public void confirmarVoto() {
        int numero = 0;
        System.out.println("CLICOU O BOTAO DE VOTO!");

        if (!numeroDigitado.equals("BRANCO")) {
            System.out.println(numeroDigitado);
            numero = Integer.parseInt(numeroDigitado);
            System.out.println(numero);
            votacaoService.registrarVoto(numero);
            init();
        }

        numeroDigitado = ""; // limpa
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Voto computado!", null));
    }
}
