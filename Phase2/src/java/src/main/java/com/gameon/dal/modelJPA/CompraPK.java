package com.gameon.dal.modelJPA;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraPK implements Serializable {
    private Integer jogador;

    private String jogo;

    public CompraPK() {
    }

    public Integer getJogador() {
        return jogador;
    }

    public void setJogador(Integer jogador) {
        this.jogador = jogador;
    }

    public String getJogo() {
        return jogo;
    }

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompraPK compraPK)) return false;
        return Objects.equals(jogador, compraPK.jogador)
                && Objects.equals(jogo, compraPK.jogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador, jogo);
    }
}
