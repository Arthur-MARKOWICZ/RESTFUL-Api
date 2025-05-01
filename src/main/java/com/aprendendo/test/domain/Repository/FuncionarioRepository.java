package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long> {
    Funcionario findByNome(String nome);

    UserDetails findByEmail(String email);
}
