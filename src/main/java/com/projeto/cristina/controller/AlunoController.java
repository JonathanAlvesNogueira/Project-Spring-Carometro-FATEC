package com.projeto.cristina.controller;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.cristina.model.Aluno;
import com.projeto.cristina.repository.AlunoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Operation(summary = "Adiciona um aluno", method = "POST")
    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna um aluno específico", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza os dados de um aluno", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            aluno.setIdAluno(id);
            Aluno alunoAtualizado = alunoRepository.save(aluno);
            return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Exclui um aluno específico", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        if (alunoRepository.findById(id).isPresent()) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
