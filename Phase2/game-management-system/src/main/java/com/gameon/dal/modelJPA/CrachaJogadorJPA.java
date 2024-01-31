package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the cracha_jogador database table.
 */
@Entity
@Table(name = "cracha_jogador")
//@NamedQuery(name = "CrachaJogador.findAll", query = "SELECT c FROM CrachaJogador c")
public class CrachaJogadorJPA {
    @EmbeddedId
    private CrachaJogadorPK id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorJPA jogador;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "cracha_id")
    private CrachaJPA cracha;

    public CrachaJogadorJPA() {
    }

    public CrachaJogadorPK getId() {
        return id;
    }

    public void setId(CrachaJogadorPK id) {
        this.id = id;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public CrachaJPA getCracha() {
        return this.cracha;
    }

    public void setCracha(CrachaJPA cracha) {
        this.cracha = cracha;
    }
}