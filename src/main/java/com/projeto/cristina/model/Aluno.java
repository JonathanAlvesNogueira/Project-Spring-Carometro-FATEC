package com.projeto.cristina.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;
    private String nomeString;
    private int idade;
    private String linkLinkedin;
    private String linkGithub;

    // Construtores
    public Aluno() {
    }

    public Aluno(Long idAluno, String nomeString, int idade, String linkLinkedin, String linkGithub) {
        this.idAluno = idAluno;
        this.nomeString = nomeString;
        this.idade = idade;
        this.linkLinkedin = linkLinkedin;
        this.linkGithub = linkGithub;
    }

    // Getters e Setters
    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeString() {
        return nomeString;
    }

    public void setNomeString(String nomeString) {
        this.nomeString = nomeString;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLinkLinkedin() {
        return linkLinkedin;
    }

    public void setLinkLinkedin(String linkLinkedin) {
        this.linkLinkedin = linkLinkedin;
    }

    public String getLinkGithub() {
        return linkGithub;
    }

    public void setLinkGithub(String linkGithub) {
        this.linkGithub = linkGithub;
    }
}
