package com.gameon.model;

import com.gameon.dal.modelJPA.ParticipacaoConversaPK;

public class ParticipacaoConversa {

    private Jogador jogador;
    private Conversa conversa;

    public ParticipacaoConversa() {
    }

    public ParticipacaoConversaPK getId() {
        ParticipacaoConversaPK id = new ParticipacaoConversaPK();
        id.setJogador(jogador.getId());
        id.setConversa(conversa.getId());
        return id;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Conversa getConversa() {
        return this.conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }
}