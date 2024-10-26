package com.projeto.cristina.controller;

import com.projeto.cristina.model.Curso;
import com.projeto.cristina.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Adiciona um curso", method = "POST")
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.ok(savedCurso);
    }

    @Operation(summary = "Retorna um curso específico", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza os dados de um curso", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
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
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
