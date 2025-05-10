package com.Restfulapi.domain.model.Funcionario;

import com.Restfulapi.domain.model.enderco.EnderecoDTO;

public record FuncionarioCadastroDTO(String nome, EnderecoDTO endereco, double numeroDeHorasPorDia,
                                     String telefone,String email,String senha) {
}
