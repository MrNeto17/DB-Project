package com.gameon.model;

public class PartidaNormal extends Partida {
    private Partida partida;
    private Jogador jogador;
    private Integer dificuldade;
    private Integer pontuacao;

    public PartidaNormal() {
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getDificuldade() {
        return this.dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
    }
}

