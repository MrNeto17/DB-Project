package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * The persistent class for the partida database table.
 */
@Entity
@Table(name = "partida")
//@NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p")
public class PartidaJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jogo_referencia", nullable = false)
    private JogoJPA jogo;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "regiao_id", nullable = false)
    private RegiaoJPA regiao;

    public PartidaJPA() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JogoJPA getJogo() {
        return this.jogo;
    }

    public void setJogo(JogoJPA jogo) {
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

    public RegiaoJPA getRegiao() {
        return this.regiao;
    }

    public void setRegiao(RegiaoJPA regiao) {
        this.regiao = regiao;
    }
}

