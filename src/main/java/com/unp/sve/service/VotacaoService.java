package com.unp.sve.service;

import com.unp.sve.domain.Candidato;
import com.unp.sve.domain.Pessoa;
import com.unp.sve.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VotacaoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public void registrarVoto(int numeroDigitado) {
        Candidato candidato = candidatoRepository.findByNumero(numeroDigitado);

        if (candidato != null) {
            candidato.setVotos(candidato.getVotos() + 1);
            candidato.setDataVoto(new Date());
            candidatoRepository.save(candidato);
            System.out.println("Voto computado com sucesso!");
        } else {
            System.out.println("Candidato não encontrado: " + numeroDigitado);
        }
    }



}
