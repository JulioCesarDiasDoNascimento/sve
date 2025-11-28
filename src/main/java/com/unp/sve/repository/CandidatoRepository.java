package com.unp.sve.repository;

import com.unp.sve.domain.Candidato;
import com.unp.sve.domain.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    Candidato findByNumero(int numero);
}
