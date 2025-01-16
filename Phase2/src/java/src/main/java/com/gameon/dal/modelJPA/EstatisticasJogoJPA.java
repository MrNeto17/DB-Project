package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the estatisticas_jogo database table.
 */
@Entity
@Table(name = "estatisticas_jogo")
//@NamedQuery(name = "EstatisticasJogo.findAll", query = "SELECT e FROM EstatisticasJogo e")
public class EstatisticasJogoJPA {
    @Id
    @ManyToOne
    @JoinColumn(name = "jogo_referencia")
    private JogoJPA jogo;

    @Column(name = "numeros_partidas", nullable = false)
    private Integer numerosPartidas;

    @Column(name = "numeros_jogadores", nullable = false)
    private Integer numerosJogadores;

    @Column(name = "total_pontos", nullable = false)
    private Integer totalPontos;

    public EstatisticasJogoJPA() {
    }

    public JogoJPA getJogo() {
        return this.jogo;
    }

    public void setJogo(JogoJPA jogo) {
        this.jogo = jogo;
    }

    public Integer getNumerosPartidas() {
        return this.numerosPartidas;
    }

    public void setNumerosPartidas(Integer numerosPartidas) {
        this.numerosPartidas = numerosPartidas;
    }

    public Integer getNumerosJogadores() {
        return this.numerosJogadores;
    }

    public void setNumerosJogadores(Integer numerosJogadores) {
        this.numerosJogadores = numerosJogadores;
    }

    public Integer getTotalPontos() {
        return this.totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }
}

