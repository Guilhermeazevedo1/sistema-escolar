package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    //DÚVIDA, eu deixo o username ou coloco o cpf?

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.encontrarLoginUsuario(username);
    }
}
