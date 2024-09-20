package com.projeto.cristina.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_turma;

    private String nomeTurma;

    @ManyToOne
    @JoinColumn(name = "id")
    private Curso curso;

    @ManyToMany
    private List<Aluno> alunos = new ArrayList<>();

	public Turma(Long id, String nomeTurma, Curso curso, List<Aluno> alunos) {
		super();
		this.id_turma = id;
		this.nomeTurma = nomeTurma;
		this.curso = curso;
		this.alunos = alunos;
	}

	public Long getId() {
		return id_turma;
	}

	public void setId(Long id) {
		this.id_turma = id;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	

    
    
      
    // Getters e Setters
}
