package com.aprendendo.test.service
        ;

import com.aprendendo.test.domain.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> getall();
    User criar(User usercriado);
}
