package com.aprendendo.test.domain.model.Funcionario;

import com.aprendendo.test.domain.model.enderco.Endereco;
import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record FuncionarioCadastroDTO(String nome, EnderecoDTO endereco, double numeroDeHorasPorDia,
                                     String telefone,String email,String senha) {
}
