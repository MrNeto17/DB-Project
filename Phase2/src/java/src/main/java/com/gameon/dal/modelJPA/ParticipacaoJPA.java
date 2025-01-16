package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the participacao database table.
 */
@Entity
@Table(name = "participacao")
//@NamedQuery(name = "Participacao.findAll", query = "SELECT p FROM Participacao p")
public class ParticipacaoJPA {
    @EmbeddedId
    private ParticipacaoPK id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorJPA jogador;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "partida_multi_id")
    private PartidaJPA partidaMulti;

    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    public ParticipacaoJPA() {
    }

    public ParticipacaoPK getId() {
        return id;
    }

    public void setId(ParticipacaoPK id) {
        this.id = id;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public PartidaJPA getPartidaMulti() {
        return this.partidaMulti;
    }

    public void setPartidaMulti(PartidaJPA partidaMulti) {
        this.partidaMulti = partidaMulti;
    }

    public Integer getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}

