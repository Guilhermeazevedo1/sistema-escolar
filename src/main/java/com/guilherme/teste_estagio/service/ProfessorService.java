package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.model.Professor;
import com.guilherme.teste_estagio.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public Professor salvarProfessor(Professor professor){
        if (professor.getNome() == null || professor.getCpf() == null || professor.getSenha() == null
        || professor.getData_nascimento() == null || professor.getEscola() == null){
            throw new RuntimeException("Preencha todos os campos de forma correta");
        }
        return professorRepository.save(professor);
    }

    public List<Professor> listarTodosProfessores(){
        return professorRepository.findAll();
    }

    public Professor encontrarIdProfessor(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Número informado não existe, informe um número existente"));
    }

    public Professor atualizar(Long id, Professor professorAtualizado){
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Número informado não existe, informe um número existente"));

        professorExistente.setNome(professorAtualizado.getNome());
        professorExistente.setCpf(professorAtualizado.getCpf());
        professorExistente.setSenha(professorAtualizado.getSenha());
        professorExistente.setData_nascimento(professorAtualizado.getData_nascimento());
        professorExistente.setEscola(professorAtualizado.getEscola());

        return professorRepository.save(professorExistente);
    }
}
