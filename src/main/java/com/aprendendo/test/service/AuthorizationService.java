package com.aprendendo.test.service;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Override
    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {

        return funcionarioRepository.findByEmail(username);
    }
}
