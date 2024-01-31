package com.gameon.model;

public class Cracha {
    private Integer id;
    private Jogo jogo;
    private String nome;
    private Integer limitePontos;
    private String urlImagem;

    public Cracha() {
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