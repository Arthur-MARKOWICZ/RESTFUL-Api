package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long> {
    Funcionario findByNome(String nome);
}
