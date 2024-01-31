package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the cracha database table.
 */
@Entity
@Table(name = "cracha")
//@NamedQuery(name = "Cracha.findAll", query = "SELECT c FROM Cracha c")
public class CrachaJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jogo_referencia")
    private JogoJPA jogo;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "limite_pontos", nullable = false)
    private Integer limitePontos;

    @Column(name = "url_imagem", nullable = false)
    private String urlImagem;

    public CrachaJPA() {
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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getLimitePontos() {
        return this.limitePontos;
    }

    public void setLimitePontos(Integer limitePontos) {
        this.limitePontos = limitePontos;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}