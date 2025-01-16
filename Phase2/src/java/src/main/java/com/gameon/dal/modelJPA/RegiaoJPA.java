package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the regiao database table.
 */
@Entity
@Table(name = "regiao")
//@NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r")
public class RegiaoJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    public RegiaoJPA() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
