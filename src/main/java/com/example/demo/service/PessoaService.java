package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired(required = false)
    PessoaRepository pessoaRepository;
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.getById(id);
    }
}
