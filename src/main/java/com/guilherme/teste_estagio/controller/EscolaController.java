package com.guilherme.teste_estagio.controller;

import com.guilherme.teste_estagio.model.Escola;
import com.guilherme.teste_estagio.service.EscolaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class EscolaController {

    private final EscolaService escolaService;

    public EscolaController(EscolaService escolaService){
        this.escolaService = escolaService;
    }

    @PostMapping
    public ResponseEntity<Escola> salvarEscola(@RequestBody Escola escola){
        return ResponseEntity.status(HttpStatus.CREATED).body(escolaService.salvarEscola(escola));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> encontrarIdEscola(@PathVariable Long id){
        return ResponseEntity.ok(escolaService.listarEscolaId(id));
    }

    @GetMapping
    public ResponseEntity<List<Escola>> listarTodasEscolas(){
        return ResponseEntity.ok(escolaService.listarTodasEscolas());
    }

    @PutMapping
    public ResponseEntity<Escola> atualizarEscola(@PathVariable Long id, @RequestBody Escola escolaAtualizada){
        Escola escola = escolaService.atualizarEscola(id, escolaAtualizada);
        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Escola> deletarEscola(@PathVariable Long id){
        escolaService.deletarEscola(id);
        return ResponseEntity.noContent().build();
    }

}
