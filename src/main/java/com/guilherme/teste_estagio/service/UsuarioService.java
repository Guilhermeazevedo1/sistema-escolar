package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.model.Usuario;
import com.guilherme.teste_estagio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario){
        if(usuario.getNome() == null || usuario.getCpf() == null || usuario.getSenha() == null
        || usuario.getData_nascimento() == null){
            throw new RuntimeException("Os dados não estão completos");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario listarIdUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O número informado não existe, informe um número existente"));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Número informado não existe, informe um número existente"));

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setCpf(usuarioAtualizado.getNome());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setData_nascimento(usuarioAtualizado.getData_nascimento());

        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else {
            throw  new RuntimeException("Número informado não existe, informe um número existente");
        }

    }
}
