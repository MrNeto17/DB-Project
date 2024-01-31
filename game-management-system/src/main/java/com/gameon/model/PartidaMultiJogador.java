package com.gameon.model;

public class PartidaMultiJogador extends Partida {
    private Partida partida;
    private Estado estado;

    public PartidaMultiJogador() {
    }

    public Partida getPartida() {
        return this.partida;
    }

    public void setPartida(Partida partida) {
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