package com.guilherme.teste_estagio.DTO;

import com.guilherme.teste_estagio.role.UsuarioRole;

public record RegisterDTO(String cpf, String senha, UsuarioRole role) {
}
