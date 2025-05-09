package com.Restfulapi.service;

import com.Restfulapi.domain.model.Funcionario.*;


import java.util.List;


public interface FuncionarioService {
    Funcionario findById(Long id);
    List<Funcionario> getall();
    Funcionario criar(Funcionario funcionariocriado);

    FuncionarioAlterarDadosResponseDTO alterarDados(Long id, FuncionarioAlterarDadosDTO dados);

    Funcionario buscarPorId(Long id);

    FuncionarioAlterarRoleResponseDTO alterarRole(Long id, AtualizarRoleDTO dto);
}
