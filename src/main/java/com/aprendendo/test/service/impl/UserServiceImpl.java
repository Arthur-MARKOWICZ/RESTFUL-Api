package com.aprendendo.test.service.impl;

import com.aprendendo.test.domain.Repository.UserRepository;
import com.aprendendo.test.domain.model.User.User;
import com.aprendendo.test.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public User criar(User usercriado) {
        User usuarioExistente = userRepository.findByNome(usercriado.getNome());
        if (usuarioExistente != null && usuarioExistente.getNome().equals(usercriado.getNome())){
            throw  new IllegalArgumentException("this user already exists  ");
        }
        return userRepository.save(usercriado);
    }
}
