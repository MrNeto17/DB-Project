package com.gameon.dal.modelJPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The persistent class for the jogo database table.
 */
@Entity
@Table(name = "jogo")
//@NamedQuery(name = "Jogo.findAll", query = "SELECT j FROM Jogo j")
public class JogoJPA {
    @Id
    @Column(length = 10)
    private String referencia;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "url", nullable = false)
    private String url;

    public JogoJPA() {
    }

    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

