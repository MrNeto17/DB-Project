package com.gameon.model;

public class Jogador {
    private Integer id;
    private String email;
    private String username;
    private Estado estado;
    private Regiao regiao;

    public Jogador() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Regiao getRegiao() {
        return this.regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public enum Estado {
        Ativo,
        Inativo,
        Banido
    }
}