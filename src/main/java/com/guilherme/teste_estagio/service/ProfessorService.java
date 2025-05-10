package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.model.Escola;
import com.guilherme.teste_estagio.model.Professor;
import com.guilherme.teste_estagio.model.Usuario;
import com.guilherme.teste_estagio.repository.ProfessorRepository;
import com.guilherme.teste_estagio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfessorService(ProfessorRepository professorRepository, UsuarioRepository usuarioRepository){
        this.professorRepository = professorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Professor salvarProfessor(Professor professor, Escola escola, Usuario usuario){
        if (professor.getNome() == null || professor.getCpf() == null || professor.getSenha() == null
        || professor.getData_nascimento() == null || professor.getEscola() == null){
            throw new RuntimeException("Preencha todos os campos de forma correta");
        }
        if(professorRepository.existsById(escola.getId())){
            usuarioRepository.save(usuario);
            return professorRepository.save(professor);
        }else {
            throw new RuntimeException("O número da escola não existe, Coloque um número válido");
        }


    }

    public List<Professor> listarTodosProfessores(){
        return professorRepository.findAll();
    }

    public Professor encontrarIdProfessor(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Número informado não existe, informe um número existente"));
    }

    public Professor atualizarProfessor(Long id, Professor professorAtualizado){
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Número informado não existe, informe um número existente"));

        professorExistente.setNome(professorAtualizado.getNome());
        professorExistente.setCpf(professorAtualizado.getCpf());
        professorExistente.setSenha(professorAtualizado.getSenha());
        professorExistente.setData_nascimento(professorAtualizado.getData_nascimento());
        professorExistente.setEscola(professorAtualizado.getEscola());

        return professorRepository.save(professorExistente);
    }

    public void deletarProfessor(Long id){
        if (professorRepository.existsById(id)){
            professorRepository.deleteById(id);
        }else {
            throw new RuntimeException("Número informado não existe, informe um número válido");
        }
    }
}
