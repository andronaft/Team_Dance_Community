package com.zuk.service;

import com.zuk.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findByEmail(String email);

    ArrayList<User> findByFirstName(String firstName);

    ArrayList<User> findByLastName(String lastName);

    ArrayList<User> findByFirstNameAndLastName(String firstName, String lastName);

    User findById(Long id);

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    User activateUser(Long id);

    User updatePassword(String password, Long id);

    User setRoleTrainer(Long id);

    void delete(Long id);



    User findTrainerByUsername(String username);
}
