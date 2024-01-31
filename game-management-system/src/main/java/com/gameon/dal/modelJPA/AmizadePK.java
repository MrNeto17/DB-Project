package com.gameon.dal.modelJPA;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AmizadePK implements Serializable {
    private Integer jogadorId1;

    private Integer jogadorId2;

    public AmizadePK() {
    }

    public Integer getJogadorId1() {
        return this.jogadorId1;
    }

    public void setJogadorId1(Integer jogadorId1) {
        this.jogadorId1 = jogadorId1;
    }

    public Integer getJogadorId2() {
        return this.jogadorId2;
    }

    public void setJogadorId2(Integer jogadorId2) {
        this.jogadorId2 = jogadorId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmizadePK amizadePK)) return false;
        return Objects.equals(jogadorId1, amizadePK.jogadorId1)
                && Objects.equals(jogadorId2, amizadePK.jogadorId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogadorId1, jogadorId2);
    }
}
