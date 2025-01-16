package com.gameon.dal.modelJPA;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipacaoPK implements Serializable {
    private Integer jogador;

    private Integer partidaMulti;

    public ParticipacaoPK() {
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public Integer getPartidaMulti() {
        return partidaMulti;
    }

    public void setPartidaMulti(Integer partidaMulti) {
        this.partidaMulti = partidaMulti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipacaoPK that)) return false;
        return Objects.equals(jogador, that.jogador)
                && Objects.equals(partidaMulti, that.partidaMulti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador, partidaMulti);
    }
}
