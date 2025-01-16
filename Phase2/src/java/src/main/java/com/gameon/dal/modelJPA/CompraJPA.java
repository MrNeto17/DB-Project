package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * The persistent class for the compra database table.
 */
@Entity
@Table(name = "compra")
//@NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c")
public class CompraJPA {
    @EmbeddedId
    private CompraPK id;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private JogadorJPA jogador;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "jogo_referencia", nullable = false)
    private JogoJPA jogo;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "preco", nullable = false)
    private Double preco;

    public CompraJPA() {
    }

    public CompraPK getId() {
        return id;
    }

    public void setId(CompraPK id) {
        this.id = id;
    }

    public JogadorJPA getJogador() {
        return this.jogador;
    }

    public void setJogador(JogadorJPA jogador) {
        this.jogador = jogador;
    }

    public JogoJPA getJogo() {
        return this.jogo;
    }

    public void setJogo(JogoJPA jogo) {
        this.jogo = jogo;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}