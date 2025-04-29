package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
     User findByNome(String nome);

    UserDetails findByEmail(String email);
}
