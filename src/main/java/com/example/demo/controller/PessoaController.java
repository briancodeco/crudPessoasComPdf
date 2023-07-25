package com.example.demo.controller;

import com.example.demo.geradorRelatorio.RelatorioSimples;
import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired(required = false)
    PessoaService pessoaService;

    @PostMapping(value = "/cadastrar", consumes = {"application/xml","application/json"})
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.salvar(pessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);    }
    @GetMapping("/listar")
    public List<Pessoa> listar(){
        return pessoaService.listar();
    }
    @DeleteMapping("/deletar/{id}")
    public String deletar(Long id) {
        try{
            pessoaService.deletar(id);
        } catch (Exception e ){
            e.printStackTrace();
        }
        return "Usuario Deletado Com sucesso";
    }

    @PutMapping
    public Pessoa alterar(@RequestBody Pessoa pessoa){
        Pessoa aux =  pessoaService.findById(pessoa.getId());
        aux.setCpf(pessoa.getCpf());
        aux.setNome(pessoa.getNome());
        aux.setEmail(pessoa.getEmail());
        aux.setTelefone(pessoa.getTelefone());
        return pessoaService.salvar(aux);
    }

   @GetMapping("/gerar-relatorio")
    public void ClienteGerarRelatorio() throws DocumentException {
        List<Pessoa> lista = new ArrayList<>();
        lista = this.listar();
        try {
            RelatorioSimples relatorioSimples = new RelatorioSimples(lista);
            relatorioSimples.gerarCabecalho();
            relatorioSimples.gerarCorpo();
            relatorioSimples.imprimir();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }




}
