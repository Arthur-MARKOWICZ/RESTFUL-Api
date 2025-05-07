package com.aprendendo.test.service.impl;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.model.Funcionario.*;
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
        Funcionario funcionarioExistente  =funcionarioRepository.findByNome(funcionariocriado.getNome());
        if (funcionarioExistente != null && funcionarioExistente.getNome().equals(funcionariocriado.getNome())){
            throw  new IllegalArgumentException("este funcionario ja esta cadastrado ");
        }
        return funcionarioRepository.save(funcionariocriado);
    }

    @Override
    public FuncionarioAlterarDadosResponseDTO alterarDados(Long id, FuncionarioAlterarDadosDTO dados) {
        Funcionario funcionarioAchado = funcionarioRepository.getReferenceById(id);
        if(funcionarioAchado == null){
            throw  new IllegalArgumentException("funcionario nao encontrado");
        }
        funcionarioAchado.alterarDados(dados);

        FuncionarioAlterarDadosResponseDTO dto = new FuncionarioAlterarDadosResponseDTO(funcionarioAchado);
        return dto;
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
        return funcionario;
    }

    @Override
    public FuncionarioAlterarRoleResponseDTO alterarRole(Long id, AtualizarRoleDTO dto) {
        Funcionario funcionarioAchado = funcionarioRepository.getReferenceById(id);
        funcionarioAchado.setRole(dto.role());
        funcionarioRepository.save(funcionarioAchado);
        FuncionarioAlterarRoleResponseDTO dtoAlterar = new FuncionarioAlterarRoleResponseDTO(funcionarioAchado);
        return dtoAlterar;
    }


}
