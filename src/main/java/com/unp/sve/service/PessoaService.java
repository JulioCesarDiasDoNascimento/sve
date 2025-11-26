package com.unp.sve.service;

import com.unp.sve.domain.Pessoa;
import com.unp.sve.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
