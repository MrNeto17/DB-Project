package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the amizade database table.
 */
@Entity
@Table(name = "amizade")
//@NamedQuery(name = "Amizade.findAll", query = "SELECT a FROM Amizade a")
public class AmizadeJPA {
    @EmbeddedId
    private AmizadePK id;

    @MapsId("jogadorId1")
    @ManyToOne
    @JoinColumn(name = "jogador_id1", nullable = false)
    private JogadorJPA jogador1;

    @MapsId("jogadorId2")
    @ManyToOne
    @JoinColumn(name = "jogador_id2", nullable = false)
    private JogadorJPA jogador2;

    public AmizadeJPA() {
    }

    public AmizadePK getId() {
        return id;
    }

    public void setId(AmizadePK id) {
        this.id = id;
    }

    public JogadorJPA getJogador1() {
        return jogador1;
    }

    public void setJogador1(JogadorJPA jogador1) {
        this.jogador1 = jogador1;
    }

    public JogadorJPA getJogador2() {
        return jogador2;
    }

    public void setJogador2(JogadorJPA jogador2) {
        this.jogador2 = jogador2;
    }
}