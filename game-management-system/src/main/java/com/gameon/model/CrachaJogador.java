package com.gameon.model;

import com.gameon.dal.modelJPA.CrachaJogadorPK;

public class CrachaJogador {

    private Jogador jogador;
    private Cracha cracha;

    public CrachaJogador() {
    }

    public CrachaJogadorPK getId() {
        CrachaJogadorPK id = new CrachaJogadorPK();
        id.setJogador(jogador.getId());
        id.setCracha(cracha.getId());
        return id;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Cracha getCracha() {
        return this.cracha;
    }

    public void setCracha(Cracha cracha) {
        this.cracha = cracha;
    }
}