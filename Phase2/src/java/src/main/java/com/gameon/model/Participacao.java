package com.gameon.model;

import com.gameon.dal.modelJPA.ParticipacaoPK;

public class Participacao {

    private Jogador jogador;
    private Partida partidaMulti;
    private Integer pontuacao;

    public Participacao() {
    }

    public ParticipacaoPK getId() {
        ParticipacaoPK id = new ParticipacaoPK();
        id.setJogador(jogador.getId());
        id.setPartidaMulti(partidaMulti.getId());
        return id;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Partida getPartidaMulti() {
        return this.partidaMulti;
    }

    public void setPartidaMulti(Partida partidaMulti) {
        this.partidaMulti = partidaMulti;
    }

    public Integer getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}

