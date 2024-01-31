package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the partida_normal database table.
 */
@Entity
@Table(name = "partida_normal")
//@NamedQuery(name = "PartidaNormal.findAll", query = "SELECT p FROM PartidaNormal p")
public class PartidaNormalJPA extends PartidaJPA {
    @Id
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private PartidaJPA partida;

    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private JogadorJPA jogador;

    @Column(name = "dificuldade", nullable = false)
    private Integer dificuldade;

    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    public PartidaNormalJPA() {
    }

    public PartidaJPA getPartida() {
        return this.partida;
    }

    public void setPartida(PartidaJPA partida) {
        this.partida = partida;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public Integer getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getDificuldade() {
        return this.dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
    }
}

