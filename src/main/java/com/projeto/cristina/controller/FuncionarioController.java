package com.projeto.cristina.controller;

import com.projeto.cristina.model.Funcionario;
import com.projeto.cristina.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController implements IController<Funcionario>{

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Operation(summary = "Adiciona um funcionário", method = "POST")
    @PostMapping
    @Override
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }


    @Operation(summary = "Lista todos os funcionários", method = "GET")
    @GetMapping
    @Override
    public ResponseEntity<List<Funcionario>> list() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um funcionário específico", method = "GET")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza os dados de um funcionário", method = "PUT")
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Funcionario> update(
            @PathVariable Long id,
            @RequestBody Funcionario funcionarioAtualizado) {

        Optional<Funcionario> funcionarioExistente = funcionarioRepository.findById(id);
        if (funcionarioExistente.isPresent()) {
            Funcionario funcionario = funcionarioExistente.get();
            // Atualiza os campos com os valores enviados no JSON
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCodigo(funcionarioAtualizado.getCodigo());

            // Salva as alterações no banco de dados
            Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
            return new ResponseEntity<>(funcionarioSalvo, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Exclui um funcionário específico", method = "DELETE")
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (funcionarioRepository.findById(id).isPresent()) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
