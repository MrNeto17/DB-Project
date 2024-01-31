package com.gameon.model;

import java.time.LocalDateTime;

public class Mensagem {
    private Integer id;
    private Conversa conversa;
    private Integer ordem;
    private Jogador jogador;
    private LocalDateTime dataHora;
    private String texto;

    public Mensagem() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return this.conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public Integer getOrdem() {
        return this.ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public void setJogador(Jogador jogador) {
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