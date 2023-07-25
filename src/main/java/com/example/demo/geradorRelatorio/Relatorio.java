package com.example.demo.geradorRelatorio;

import com.example.demo.model.Pessoa;

import java.util.List;

public interface Relatorio {
    public void gerarCabecalho();
    public void gerarCorpo ();
    public void gerarRodape();
    public void imprimir();

}
