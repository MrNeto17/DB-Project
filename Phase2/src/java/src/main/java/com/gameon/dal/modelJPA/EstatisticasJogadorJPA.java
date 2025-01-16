package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogador database table.
 */
@Entity
@Table(name = "estatisticas_jogador")
//@NamedQuery(name = "EstatisticasJogador.findAll", query = "SELECT e FROM EstatisticasJogador e")
public class EstatisticasJogadorJPA {
    @Id
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private JogadorJPA jogador;

    @Column(name = "numeros_partidas", nullable = false)
    private Integer numerosPartidas;

    @Column(name = "numeros_jogos", nullable = false)
    private Integer numerosJogos;

    @Column(name = "total_pontos", nullable = false)
    private Integer totalPontos;

    public EstatisticasJogadorJPA() {
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public Integer getNumerosPartidas() {
        return this.numerosPartidas;
    }

    public void setNumerosPartidas(Integer numerosPartidas) {
        this.numerosPartidas = numerosPartidas;
    }

    public Integer getNumerosJogos() {
        return this.numerosJogos;
    }

    public void setNumerosJogos(Integer numerosJogos) {
        this.numerosJogos = numerosJogos;
    }

    public Integer getTotalPontos() {
        return this.totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }
}

