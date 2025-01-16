package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the participacao_conversa database table.
 */
@Entity
@Table(name = "participacao_conversa")
//@NamedQuery(name = "ParticipacaoConversa.findAll", query = "SELECT p FROM ParticipacaoConversa p")
public class ParticipacaoConversaJPA {
    @EmbeddedId
    private ParticipacaoConversaPK id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorJPA jogador;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "conversa_id")
    private ConversaJPA conversa;

    public ParticipacaoConversaJPA() {
    }

    public ParticipacaoConversaPK getId() {
        return id;
    }

    public void setId(ParticipacaoConversaPK id) {
        this.id = id;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public ConversaJPA getConversa() {
        return this.conversa;
    }

    public void setConversa(ConversaJPA conversa) {
        this.conversa = conversa;
    }
}