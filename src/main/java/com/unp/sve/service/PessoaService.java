package com.unp.sve.service;

import com.unp.sve.domain.Pessoa;
import com.unp.sve.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        if (pessoa.getEmail() == null || pessoa.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email obrigatório");
        }
        if (pessoa.getCpf() == null || pessoa.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF obrigatório");
        }
        if (pessoaRepository.existsByEmail(pessoa.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> validarLogin(String cpf, String senha) {
        if (cpf == null || cpf.isBlank() || senha == null || senha.isBlank()) {
            return Optional.empty();
        }
        return pessoaRepository.findByCpf(cpf)
                .filter(p -> senha.equals(p.getSenha()));
    }

    // New helper to list all Pessoas (used by controllers)
    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    // Convenience method to list only Candidatos
    public List<com.unp.sve.domain.Candidato> listarCandidatos() {
        return pessoaRepository.findAll().stream()
                .filter(p -> p instanceof com.unp.sve.domain.Candidato)
                .map(p -> (com.unp.sve.domain.Candidato) p)
                .collect(Collectors.toList());
    }

    public Optional<Pessoa> buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public Pessoa atualizar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
