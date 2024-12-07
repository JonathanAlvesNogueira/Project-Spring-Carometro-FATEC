package com.projeto.cristina.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTurma;

	private String periodo;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
    private Curso curso;

	@JsonBackReference
	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Aluno> alunos;

	public Turma() {

	}

	public Turma(Long id, String nomeTurma, Curso curso, List<Aluno> alunos) {
		super();
		this.id = id;
		this.nomeTurma = nomeTurma;
		this.curso = curso;
		this.alunos = alunos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

}
