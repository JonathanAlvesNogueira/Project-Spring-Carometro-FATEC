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

import com.projeto.cristina.model.Curso;
import com.projeto.cristina.repository.CursoRepository;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cursos")
public class CursoController implements IController<Curso>{

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Adiciona um curso", method = "POST")
    @PostMapping
    @Override
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(savedCurso);
    }

    @Operation(summary = "Retorna um curso específico", method = "GET")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza os dados de um curso", method = "PUT")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        Optional<Curso> existingCurso = cursoRepository.findById(id);
        if (existingCurso.isPresent()) {
            Curso curso = existingCurso.get();
            curso.setNomeCurso(cursoDetails.getNomeCurso());
            curso.setCodigoCurso(cursoDetails.getCodigoCurso());
            curso.setDuracaoEmSemestres(cursoDetails.getDuracaoEmSemestres());
            curso.setDepartamento(cursoDetails.getDepartamento());
            curso.setCoordenador(cursoDetails.getCoordenador());
            curso.setNumeroDeVagas(cursoDetails.getNumeroDeVagas());
            curso.setTurno(cursoDetails.getTurno());
            curso.setModalidade(cursoDetails.getModalidade());
            Curso updatedCurso = cursoRepository.save(curso);
            return ResponseEntity.ok(updatedCurso);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Exclui um curso específico", method = "DELETE")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Lista todos os cursos", method = "GET")
    @GetMapping
	@Override
	public ResponseEntity<List<Curso>> list() {
    	List<Curso> cursos = cursoRepository.findAll();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
	}
}
