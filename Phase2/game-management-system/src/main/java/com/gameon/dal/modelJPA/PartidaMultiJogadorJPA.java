package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the jogador_multi_jogador database table.
 */
@Entity
@Table(name = "partida_multi_jogador")
//@NamedQuery(name = "PartidaMultiJogador.findAll", query = "SELECT p FROM PartidaMultiJogador p")
public class PartidaMultiJogadorJPA extends PartidaJPA {
    @Id
    @ManyToOne
    @JoinColumn(name = "partida_multi_id")
    private PartidaJPA partida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    public PartidaMultiJogadorJPA() {
    }

    public PartidaJPA getPartida() {
        return this.partida;
    }

    public void setPartida(PartidaJPA partida) {
        this.partida = partida;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public enum Estado {
        por_iniciar,
        a_aguardar_jogadores,
        em_curso,
        Terminada
    }
}