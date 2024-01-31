package com.gameon.dal.modelJPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogadorTotalInfo")
public class JogadorTotalInfoJPA {
    @Id
    private Integer jogadorId;
    @Column(name = "estado")
    private String estado;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "numero_total_de_jogos")
    private Integer numeroTotalDeJogos;
    @Column(name = "numero_total_de_partidas")
    private Integer numeroTotalDePartidas;
    @Column(name = "numero_total_de_pontos")
    private Integer numeroTotalDePontos;

    public JogadorTotalInfoJPA() {
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


