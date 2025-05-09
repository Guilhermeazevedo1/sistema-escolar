package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.model.Aluno;
import com.guilherme.teste_estagio.model.Professor;
import com.guilherme.teste_estagio.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    //dúvida
    public Aluno salvarAluno(Aluno aluno, Professor professor){

        if(aluno.getNome() == null || aluno.getCpf() == null || aluno.getData_nascimento() == null
        || aluno.getProfessor() == null){
            throw new RuntimeException("Preencha todos os campos de forma correta");
        }
        if (alunoRepository.existsById(professor.getId())){
            return alunoRepository.save(aluno);
        }else {
            throw new RuntimeException("O número do professor não existe, para o aluno ser criado o número tem que esta correto");
        }

    }

    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno encontrarIdAluno(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Número informado não existe, informe um número existente"));
    }

    public Aluno atualizarAluno(Long id, Aluno alunoAtualizado){
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Número informado não existente, informe um número existente"));

        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setCpf(alunoAtualizado.getCpf());
        alunoExistente.setData_nascimento(alunoAtualizado.getData_nascimento());
        alunoExistente.setProfessor(alunoAtualizado.getProfessor());

        return alunoRepository.save(alunoExistente);
    }

    public void deletarAluno(Long id){
        if (alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }else {
            throw new RuntimeException("Número informado não existente, informe um número existente");
        }
    }
}
