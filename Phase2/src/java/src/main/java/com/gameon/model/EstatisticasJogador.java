package com.gameon.model;

public class EstatisticasJogador {
    private Jogador jogador;
    private Integer numerosPartidas;
    private Integer numerosJogos;
    private Integer totalPontos;

    public EstatisticasJogador() {
    }


    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getNumerosPartidas() {
        return this.numerosPartidas;
    }

    public void setNumerosPartidas(Integer numerosPartidas) {
        this.numerosPartidas = numerosPartidas;
    }

    public Integer getNumerosJogos() {
        return this.numerosJogos;
    }

    public void setNumerosJogos(Integer numerosJogos) {
        this.numerosJogos = numerosJogos;
    }

    public Integer getTotalPontos() {
        return this.totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }
}

