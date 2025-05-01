package com.aprendendo.test.domain.model.User;

import com.aprendendo.test.domain.model.enderco.Endereco;
import com.aprendendo.test.domain.model.enderco.EnderecoDTO;

public record UserAlterarDadosResponseDTO(String nome, Endereco endereco, String email) {
    public UserAlterarDadosResponseDTO(User user){
        this(user.getNome(), user.getEndereco(), user.getEmail());
    }


}
