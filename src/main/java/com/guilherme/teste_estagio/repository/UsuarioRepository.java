package com.guilherme.teste_estagio.repository;

import com.guilherme.teste_estagio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails encontrarLoginUsuario(String cpf);

    //DÚVIDA
    //UserDetails encontrarSenhaUsuario(String senha);
    //UserDetails encontrarSenhaUsuario(String senha, String cpf);
}
