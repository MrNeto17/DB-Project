package com.gameon.dal.modelJPA;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CrachaJogadorPK implements Serializable {
    private Integer jogador;

    private Integer cracha;

    public CrachaJogadorPK() {
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public Integer getCracha() {
        return cracha;
    }

    public void setCracha(Integer cracha) {
        this.cracha = cracha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrachaJogadorPK that)) return false;
        return Objects.equals(jogador, that.jogador)
                && Objects.equals(cracha, that.cracha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador, cracha);
    }
}
