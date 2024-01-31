package com.gameon.model;

import com.gameon.dal.modelJPA.CompraPK;

import java.time.LocalDate;

public class Compra {
    private Jogador jogador;
    private Jogo jogo;
    private LocalDate dataCompra;
    private Double preco;

    public Compra() {
    }

    public CompraPK getId() {
        CompraPK id = new CompraPK();
        id.setJogador(jogador.getId());
        id.setJogo(jogo.getReferencia());
        return id;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}