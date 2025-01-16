package com.gameon.model;

import com.gameon.dal.modelJPA.AmizadePK;

public class Amizade {

    private Jogador jogador1;
    private Jogador jogador2;

    public Amizade() {
    }

    public AmizadePK getId() {
        AmizadePK id = new AmizadePK();
        id.setJogadorId1(jogador1.getId());
        id.setJogadorId2(jogador2.getId());
        return id;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }
}