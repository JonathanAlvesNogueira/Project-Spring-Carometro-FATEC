package com.projeto.cristina.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.cristina.model.Turma;
import com.projeto.cristina.repository.TurmaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Operation(summary = "Adiciona uma turma", method = "POST")
    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        Turma novaTurma = turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }

    @Operation(summary = "Retorna uma turma específica", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Long id) {
        return turmaRepository.findById(id)
            .map(turma -> ResponseEntity.ok().body(turma))
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza os dados de uma turma", method = "PUT")
    @GetMapping
    public List<Turma> listarTodasAsTurmas() {
        return turmaRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        return turmaRepository.findById(id)
            .map(turma -> {
                turma.setNomeTurma(turmaAtualizada.getNomeTurma());
                turma.setCurso(turmaAtualizada.getCurso());
                turma.setAlunos(turmaAtualizada.getAlunos());
                Turma turmaSalva = turmaRepository.save(turma);
                return ResponseEntity.ok().body(turmaSalva);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Exclui uma turma específica", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTurma(@PathVariable Long id) {
        return turmaRepository.findById(id)
            .map(turma -> {
                turmaRepository.delete(turma);
                return ResponseEntity.noContent().build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
