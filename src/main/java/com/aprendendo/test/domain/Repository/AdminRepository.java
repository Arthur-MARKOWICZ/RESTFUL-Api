package com.aprendendo.test.domain.Repository;

import com.aprendendo.test.domain.model.Admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    UserDetails findByEmail(String email);
}
