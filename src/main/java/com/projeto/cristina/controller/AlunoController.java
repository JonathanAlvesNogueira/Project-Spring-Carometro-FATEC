package com.projeto.cristina.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.cristina.model.Aluno;
import com.projeto.cristina.repository.AlunoRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Operation(summary = "Adiciona um aluno", method = "POST")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Aluno> cadastrarAluno(
            @RequestParam("nome") String nome,
            @RequestParam("cpf") Long cpf,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam(value = "github", required = false) String github,
            @RequestParam(value = "linkedin", required = false) String linkedin,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            @RequestParam(value = "comentario", required = false) String comentario
    ) {
        try {
            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setDataNascimento(dataNascimento);
            aluno.setGithub(github);
            aluno.setLinkedin(linkedin);
            aluno.setEmail(email);
            aluno.setSenha(senha);
            aluno.setComentario(comentario);

            // Verifica se há uma foto e, se sim, armazena em bytes
            if (foto != null && !foto.isEmpty()) {
                aluno.setFoto(foto.getBytes());
            }

            Aluno alunoSalvo = alunoRepository.save(aluno);
            return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Lista todos os alunos", method = "GET")
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
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
