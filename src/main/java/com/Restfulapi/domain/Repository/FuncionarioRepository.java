package com.Restfulapi.domain.Repository;

import com.Restfulapi.domain.model.Funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long> {
    Funcionario findByNome(String nome);

    UserDetails findByEmail(String email);
}
