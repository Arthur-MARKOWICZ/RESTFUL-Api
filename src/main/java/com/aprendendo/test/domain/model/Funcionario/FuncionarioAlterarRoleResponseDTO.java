package com.aprendendo.test.domain.model.Funcionario;

public record FuncionarioAlterarRoleResponseDTO(Long id,String nome,String email,Role role) {
    public FuncionarioAlterarRoleResponseDTO(Funcionario funcionarioAchado) {
        this(funcionarioAchado.getId(),funcionarioAchado.getNome(), funcionarioAchado.getEmail(), funcionarioAchado.getRole());
    }
}
