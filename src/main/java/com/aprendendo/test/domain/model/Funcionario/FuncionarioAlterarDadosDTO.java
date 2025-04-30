package com.aprendendo.test.domain.model.Funcionario;

import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record FuncionarioAlterarDadosDTO(String nome, EnderecoDTO endereco, double numeroDeHorasPorDia,
                                         String telefone, String email) {

}
