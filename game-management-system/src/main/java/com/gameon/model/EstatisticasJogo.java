package com.gameon.model;

public class EstatisticasJogo {
    private Jogo jogo;
    private Integer numerosPartidas;
    private Integer numerosJogadores;
    private Integer totalPontos;

    public EstatisticasJogo() {
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Integer getNumerosPartidas() {
        return this.numerosPartidas;
    }

    public void setNumerosPartidas(Integer numerosPartidas) {
        this.numerosPartidas = numerosPartidas;
    }

    public Integer getNumerosJogadores() {
        return this.numerosJogadores;
    }

    public void setNumerosJogadores(Integer numerosJogadores) {
        this.numerosJogadores = numerosJogadores;
    }

    public Integer getTotalPontos() {
        return this.totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }
}

