package com.aprendendo.test.domain.model.User;

import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record UserAlterarDadosDTO(String nome, EnderecoDTO endereco,String email) {
}
