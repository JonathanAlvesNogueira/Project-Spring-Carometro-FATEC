package com.projeto.cristina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.cristina.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
