package com.projeto.cristina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.projeto.cristina.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}