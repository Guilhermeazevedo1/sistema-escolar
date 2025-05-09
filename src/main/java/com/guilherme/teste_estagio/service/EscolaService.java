package com.guilherme.teste_estagio.service;

import com.guilherme.teste_estagio.model.Escola;
import com.guilherme.teste_estagio.repository.EscolaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscolaService {

    private final EscolaRepository escolaRepository;

    public EscolaService(EscolaRepository escolaRepository){
        this.escolaRepository = escolaRepository;
    }

    public Escola salvarEscola(Escola escola){
        if (escola.getNome() == null || escola.getEndereco() == null){
            throw new RuntimeException("Nome da escola ou endereço não foi informado");
        }
        return escolaRepository.save(escola);
    }

    public List<Escola> listarTodasEscolas(){
        return escolaRepository.findAll();
    }

    public Escola listarEscolaId(Long id){
        return escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O número informado não existe"));
    }

    public Escola atualizarEscola(Long id, Escola escolaAtualizada){
        Escola escolaExistente =escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O número informado não existe"));

        escolaExistente.setNome(escolaAtualizada.getNome());
        escolaExistente.setEndereco(escolaAtualizada.getEndereco());

        return escolaRepository.save(escolaExistente);
    }

    public void deletarEscola(Long id){
        if(escolaRepository.existsById(id)){
            escolaRepository.deleteById(id);
        }else {
            throw new RuntimeException("Número informado não existe, digite um número valido");
        }
    }
}
