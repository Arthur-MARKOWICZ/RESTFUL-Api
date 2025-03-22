package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     User findByNome(String nome);

}
