package com.aprendendo.test.domain.model.User;

public record UserDeletarDTO(String nome,String email) {
    public UserDeletarDTO(User user){
        this(user.getNome(), user.getEmail());
    }
}
