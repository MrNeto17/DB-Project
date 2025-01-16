package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * The persistent class for the mensagem database table.
 */
@Entity
@Table(name = "mensagem")
//@NamedQuery(name = "mensagem.findAll", query = "SELECT m FROM Mensagem m")
public class MensagemJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "conversa_id", unique = true)
    private ConversaJPA conversa;

    @Column(name = "ordem", nullable = false, unique = true)
    private Integer ordem;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorJPA jogador;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "texto", nullable = false)
    private String texto;

    public MensagemJPA() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConversaJPA getConversa() {
        return this.conversa;
    }

    public void setConversa(ConversaJPA conversa) {
        this.conversa = conversa;
    }

    public Integer getOrdem() {
        return this.ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}