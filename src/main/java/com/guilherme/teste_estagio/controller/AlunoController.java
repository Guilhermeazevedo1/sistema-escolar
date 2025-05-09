package com.guilherme.teste_estagio.controller;

import com.guilherme.teste_estagio.model.Aluno;
import com.guilherme.teste_estagio.model.Professor;
import com.guilherme.teste_estagio.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno, @RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvarAluno(aluno, professor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> encontrarIdAluno(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.encontrarIdAluno(id));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosAlunos(){
        return ResponseEntity.ok(alunoService.listarTodosAlunos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado){
        Aluno aluno = alunoService.atualizarAluno(id, alunoAtualizado);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
