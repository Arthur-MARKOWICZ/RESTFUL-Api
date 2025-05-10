package com.Restfulapi.domain.model.Funcionario;

import com.Restfulapi.domain.model.enderco.EnderecoDTO;

public record FuncionarioAlterarDadosDTO(String nome, EnderecoDTO endereco, double numeroDeHorasPorDia,
                                         String telefone, String email) {

}
