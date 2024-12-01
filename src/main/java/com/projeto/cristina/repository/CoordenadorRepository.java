package com.projeto.cristina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.projeto.cristina.model.Coordenador;


@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long>  {

}
