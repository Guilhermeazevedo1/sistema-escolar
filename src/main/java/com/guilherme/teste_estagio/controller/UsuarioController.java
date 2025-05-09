package com.guilherme.teste_estagio.controller;

import com.guilherme.teste_estagio.model.Usuario;
import com.guilherme.teste_estagio.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> encontrarIdUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.listarIdUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario){
        Usuario usuario = usuarioService.atualizarUsuario(id, novoUsuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
