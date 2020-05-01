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

    ArrayList<User> findByContains(String string);

    User findTrainerByUsername(String username);

    User findTrainerById(Long id);

    User findTrainerByEmail(String email);

    ArrayList<User> findAllTrainer();

    ArrayList<User> findTrainerByFirstName(String firstName);

    ArrayList<User> findTrainerByLastName(String lastName);

    ArrayList<User> findTrainerByFirstNameAndLastName(String firstName, String lastName);

}
