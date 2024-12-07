package com.projeto.cristina.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;
    private String codigoCurso;
    private int duracaoEmSemestres;
    private String departamento;

    @JsonManagedReference
    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = false)
    private Coordenador coordenador;
    private int numeroDeVagas;
    private String turno;
    private String modalidade;

    @JsonManagedReference
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Turma> turmas;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getDuracaoEmSemestres() {
        return duracaoEmSemestres;
    }

    public void setDuracaoEmSemestres(int duracaoEmSemestres) {
        this.duracaoEmSemestres = duracaoEmSemestres;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public int getNumeroDeVagas() {
        return numeroDeVagas;
    }

    public void setNumeroDeVagas(int numeroDeVagas) {
        this.numeroDeVagas = numeroDeVagas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
