package com.projeto.cristina.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFunc;

    @NotNull
    private String nome;

    @NotNull
    private String codigo;

    public Funcionario() {
    }

    public Long getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(Long idFunc) {
        this.idFunc = idFunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
