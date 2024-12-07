package com.projeto.cristina.controller;

import com.projeto.cristina.model.Coordenador;
import com.projeto.cristina.model.Curso;
import com.projeto.cristina.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Adiciona um curso", method = "POST")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Curso> createCurso(
            @RequestParam("nomeCurso") String nomeCurso,
            @RequestParam("codigoCurso") String codigoCurso,
            @RequestParam("duracaoEmSemestres") Integer duracaoEmSemestres,
            @RequestParam("departamento") String departamento,
            @RequestParam(value = "coordenador", required = false) Coordenador coordenador,
            @RequestParam("numeroDeVagas") Integer numeroDeVagas,
            @RequestParam("turno") String turno,
            @RequestParam("modalidade") String modalidade,
            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo) {

        try {
            Curso curso = new Curso();
            curso.setNomeCurso(nomeCurso);
            curso.setCodigoCurso(codigoCurso);
            curso.setDuracaoEmSemestres(duracaoEmSemestres);
            curso.setDepartamento(departamento);
            curso.setCoordenador(coordenador);
            curso.setNumeroDeVagas(numeroDeVagas);
            curso.setTurno(turno);
            curso.setModalidade(modalidade);

            Curso cursoSalvo = cursoRepository.save(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Retorna todos os cursos", method = "GET")
    @GetMapping
    public List<Curso> listCursos() {
        return cursoRepository.findAll();
    }

    @Operation(summary = "Retorna um curso específico", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza os dados de um curso", method = "PUT")
    @PutMapping
    public ResponseEntity<Curso> updateCurso(@RequestBody Curso cursoDetails) {
        Optional<Curso> existingCurso = cursoRepository.findById(cursoDetails.getId());
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
