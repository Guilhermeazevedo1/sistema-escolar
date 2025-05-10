package com.guilherme.teste_estagio.controller;

import com.guilherme.teste_estagio.model.Escola;
import com.guilherme.teste_estagio.model.Professor;
import com.guilherme.teste_estagio.model.Usuario;
import com.guilherme.teste_estagio.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> salvarProfessor(@RequestBody Professor professor, @RequestBody Escola escola,
                                                     @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.salvarProfessor(professor, escola,
                usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> encontrarIdProfessor(@PathVariable Long id){
        return ResponseEntity.ok(professorService.encontrarIdProfessor(id));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodosProfessores(){
        return ResponseEntity.ok(professorService.listarTodosProfessores());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professorAtualizado){
        Professor professor = professorService.atualizarProfessor(id, professorAtualizado);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Professor> deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
