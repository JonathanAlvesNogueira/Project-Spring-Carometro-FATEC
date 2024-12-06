package com.projeto.cristina.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController implements IController<Coordenador> {

	@Autowired
	private CoordenadorRepository coordenadorRepository;

	@Operation(summary = "Adiciona um coordenador", method = "POST")
	@PostMapping
	@Override
	public ResponseEntity<Coordenador> create(@RequestBody Coordenador coordenador) {
		Coordenador savedCoordenador = coordenadorRepository.save(coordenador);
		return ResponseEntity.ok(savedCoordenador);
	}

	@Operation(summary = "Retorna um coordenador específico", method = "GET")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<Coordenador> getById(@PathVariable Long id) {
		Optional<Coordenador> coordenador = coordenadorRepository.findById(id);
		if (coordenador.isPresent()) {
			return ResponseEntity.ok(coordenador.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Atualiza os dados de um coordenador", method = "PUT")
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<Coordenador> update(@PathVariable Long id, @RequestBody Coordenador coordenadorDetails) {
		Optional<Coordenador> existingCoordenador = coordenadorRepository.findById(id);
		if (existingCoordenador.isPresent()) {
			Coordenador coordenador = existingCoordenador.get();
			coordenador.setNome(coordenadorDetails.getNome());
			coordenador.setFuncional(coordenadorDetails.getFuncional());
			coordenador.setDataInicio(coordenadorDetails.getDataInicio());
			coordenador.setDataFim(coordenadorDetails.getDataFim());
			Coordenador updatedCoordenador = coordenadorRepository.save(coordenador);
			return ResponseEntity.ok(updatedCoordenador);
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Exclui um coordenador específico", method = "DELETE")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (coordenadorRepository.existsById(id)) {
			coordenadorRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Lista todos os coordenadores", method = "GET")
	@GetMapping
	@Override
	public ResponseEntity<List<Coordenador>> list() {
		List<Coordenador> coordenadores = coordenadorRepository.findAll();
		return new ResponseEntity<>(coordenadores, HttpStatus.OK);
	}
}
