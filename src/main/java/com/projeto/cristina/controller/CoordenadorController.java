package com.projeto.cristina.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.cristina.model.Coordenador;
import com.projeto.cristina.repository.CoordenadorRepository;

@RestController
@RequestMapping("coordenador")
public class CoordenadorController {

	@Autowired
	CoordenadorRepository repository;
	
	@GetMapping()
	public Optional<Coordenador> mostraCoordenadorCurso(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	
	@PostMapping()
	public String salvaCoordenador(@RequestBody Coordenador coord) {
		repository.save(coord);
		return "Salvo";
	}
	
	@DeleteMapping()
	public String deletaCoordenador(@PathVariable Long id) {

        if (repository.existsById(id)) {
        	repository.deleteById(id);
        }
        return "deletado";
	}
	
	@PutMapping()
	public ResponseEntity<Coordenador> atualizaCoordenador(@PathVariable Long id) {

        Optional<Coordenador> coordExistente = repository.findById(id);
        if (coordExistente.isPresent()) {
            Coordenador coord = coordExistente.get();
            	coord.setNome(coord.getNome());
            	coord.setFuncional(coord.getFuncional());
            	coord.setDataInicio(coord.getDataInicio());
            	coord.setDataFim(coord.getDataFim());   
            	Coordenador updatedcoord = repository.save(coord);
            	return ResponseEntity.ok(updatedcoord);
        }
        return ResponseEntity.notFound().build();
    }

}
