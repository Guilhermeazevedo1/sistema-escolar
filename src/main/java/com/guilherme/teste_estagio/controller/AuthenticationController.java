package com.guilherme.teste_estagio.controller;


import com.guilherme.teste_estagio.DTO.AuthenticationDTO;
import com.guilherme.teste_estagio.DTO.LoginResponseDTO;
import com.guilherme.teste_estagio.DTO.RegisterDTO;
import com.guilherme.teste_estagio.model.Usuario;
import com.guilherme.teste_estagio.repository.UsuarioRepository;
import com.guilherme.teste_estagio.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    //DÚVIDA SOBRE CADASTRO DO USUÁRIO
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO){
        if(this.repository.encontrarLoginUsuario(registerDTO.cpf()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Usuario novoUsuario = new Usuario(registerDTO.cpf(), encryptedPassword, registerDTO.role());


        this.repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
