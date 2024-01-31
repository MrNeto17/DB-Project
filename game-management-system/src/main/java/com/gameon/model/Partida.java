package com.gameon.model;

import java.time.LocalDateTime;

public class Partida {
    private Integer id;
    private Jogo jogo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Regiao regiao;

    public Partida() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jogo getJogo() {
        return this.jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public LocalDateTime getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Regiao getRegiao() {
        return this.regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }
}

