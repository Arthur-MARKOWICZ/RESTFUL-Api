package com.aprendendo.test.service;

import com.aprendendo.test.domain.model.Funcionario;

import java.util.List;


public interface FuncionarioService {
    Funcionario findById(Long id);
    List<Funcionario> getall();
    Funcionario criar(Funcionario funcionariocriado);
}
