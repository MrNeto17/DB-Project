package com.gameon.dal.modelJPA;

import jakarta.persistence.*;

/**
 * The persistent class for the jogador database table.
 */
@Entity
@Table(name = "jogador")
//@NamedQuery(name = "Jogador.findAll", query = "SELECT j FROM Jogador j")
public class JogadorJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "regiao_id", nullable = false)
    private RegiaoJPA regiao;

    public JogadorJPA() {
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

    public RegiaoJPA getRegiao() {
        return this.regiao;
    }

    public void setRegiao(RegiaoJPA regiao) {
        this.regiao = regiao;
    }

    public enum Estado {
        Ativo,
        Inativo,
        Banido
    }
}