package com.Restfulapi.domain.model.Funcionario;

import com.Restfulapi.domain.model.enderco.Endereco;

public record FuncionarioAlterarDadosResponseDTO(String nome, Endereco endereco, double numeroDeHorasPorDia,
                                                 String telefone, String email) {
    public FuncionarioAlterarDadosResponseDTO(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getEndereco(), funcionario.getNumeroDeHorasPorDia(),
                funcionario.getTelefone(), funcionario.getEmail());
    }


}
