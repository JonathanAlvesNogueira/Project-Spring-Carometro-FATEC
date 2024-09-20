package com.projeto.cristina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.cristina.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Aqui você pode adicionar métodos personalizados se necessário
}