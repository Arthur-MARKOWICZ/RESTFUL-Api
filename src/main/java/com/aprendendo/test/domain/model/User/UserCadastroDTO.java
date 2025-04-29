package com.aprendendo.test.domain.model.User;

import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record UserCadastroDTO(String nome, EnderecoDTO endereco,String email,String senha) {
}
