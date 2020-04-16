package com.zuk.service;

import com.zuk.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Long id);

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    void delete(Long id);
}
