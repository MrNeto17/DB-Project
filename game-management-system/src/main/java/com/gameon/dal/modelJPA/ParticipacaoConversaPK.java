package com.gameon.dal.modelJPA;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipacaoConversaPK implements Serializable {
    private Integer jogador;

    private Integer conversa;

    public ParticipacaoConversaPK() {
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public Integer getConversa() {
        return conversa;
    }

    public void setConversa(Integer conversa) {
        this.conversa = conversa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipacaoConversaPK that)) return false;
        return Objects.equals(jogador, that.jogador)
                && Objects.equals(conversa, that.conversa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador, conversa);
    }
}
