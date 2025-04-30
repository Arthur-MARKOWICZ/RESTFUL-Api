package com.aprendendo.test.domain.model.Funcionario;

import com.aprendendo.test.domain.model.enderco.Endereco;
import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record FuncionarioAlterarDadosResponseDTO(String nome, Endereco endereco, double numeroDeHorasPorDia,
                                                 String telefone, String email) {
    public FuncionarioAlterarDadosResponseDTO(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getEndereco(), funcionario.getNumeroDeHorasPorDia(),
                funcionario.getTelefone(), funcionario.getEmail());
    }
}
