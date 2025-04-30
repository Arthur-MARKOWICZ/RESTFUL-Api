package com.aprendendo.test.domain.model.Funcionario;

public record FuncionarioDeletarDTO(String nome,String email) {
    public FuncionarioDeletarDTO(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getEmail());
    }
}
