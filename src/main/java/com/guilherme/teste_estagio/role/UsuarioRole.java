package com.guilherme.teste_estagio.role;

public enum UsuarioRole {
    PROFESSOR("professor"),

    USUARIO("usuario");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
