package com.aprendendo.test.service.impl;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.model.Funcionario;
import com.aprendendo.test.service.FuncionarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Funcionario> getall() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario criar(Funcionario funcionariocriado) {
        if (funcionarioRepository.findByNome(funcionariocriado.getNome()).getNome().equals(funcionariocriado.getNome())){
            throw  new IllegalArgumentException("this funcionario already exists ");
        }
        return funcionarioRepository.save(funcionariocriado);
    }
}
