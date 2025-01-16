package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the conversa database table.
 */
@Entity
@Table(name = "conversa")
//@NamedQuery(name = "Conversa.findAll", query = "SELECT c FROM Conversa c")
public class ConversaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    public ConversaJPA() {
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
