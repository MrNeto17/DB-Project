package com.gameon.model;

public class JogadorTotalInfo {
    private Integer jogadorId;
    private String estado;
    private String email;
    private String username;
    private Integer numeroTotalDeJogos;
    private Integer numeroTotalDePartidas;
    private Integer numeroTotalDePontos;

    public JogadorTotalInfo() {
    }

    public Integer getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(Integer jogadorId) {
        this.jogadorId = jogadorId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNumeroTotalDeJogos() {
        return numeroTotalDeJogos;
    }

    public void setNumeroTotalDeJogos(Integer numeroTotalDeJogos) {
        this.numeroTotalDeJogos = numeroTotalDeJogos;
    }

    public Integer getNumeroTotalDePartidas() {
        return numeroTotalDePartidas;
    }

    public void setNumeroTotalDePartidas(Integer numeroTotalDePartidas) {
        this.numeroTotalDePartidas = numeroTotalDePartidas;
    }

    public Integer getNumeroTotalDePontos() {
        return numeroTotalDePontos;
    }

    public void setNumeroTotalDePontos(Integer numeroTotalDePontos) {
        this.numeroTotalDePontos = numeroTotalDePontos;
    }
}


