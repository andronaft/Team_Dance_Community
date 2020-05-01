package com.zuk.service.impl;

import com.zuk.model.Role;
import com.zuk.model.Status;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.repository.RoleRepository;
import com.zuk.repository.UserRepository;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileService userProfileService, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.NOT_ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser.getUsername());

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ArrayList<User> findByFirstName(String firstName) {
        return (ArrayList<User>) userRepository.findByFirstName(firstName);
    }

    @Override
    public ArrayList<User> findByLastName(String lastName) {
        return (ArrayList<User>) userRepository.findByLastName(lastName);
    }

    @Override
    public ArrayList<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return (ArrayList<User>) userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result.getUsername(),result.getUsername());
        return result;
    }

    @Override
    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User activateUser(Long id) {
        UserProfile userProfile = userProfileService.findById(id);
        if(userProfile == null){
            return null;
        }
        userProfile.setStatus(Status.ACTIVE);
        userProfileService.update(userProfile);
        User user = userRepository.getOne(id);
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(String password,Long id) {
        User user = userRepository.getOne(id);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User setRoleTrainer(Long id) {
        User user = userRepository.getOne(id);
        List<Role> list = user.getRoles();
        if(!list.contains(roleRepository.findByName("ROLE_TRAINER"))) {
            list.add(roleRepository.findByName("ROLE_TRAINER"));
        }
        user.setRoles(list);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted",id);
    }

    @Override
    public ArrayList<User> findByContains(String string) {
        ArrayList<User> list = new ArrayList<>();

        User userByUsername = userRepository.findByUsernameContains(string);
        if(userByUsername != null){
            list.add(userByUsername);
        }

        User userByEmail = userRepository.findByEmail(string);
        if(userByUsername != null ){
            list.add(userByEmail);
        }


        return null;
    }

    @Override
    public User findTrainerByUsername(String username) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        User user = userRepository.findByUsernameAndRolesEquals(username,roleUser);
        return user;
    }

    @Override
    public User findTrainerById(Long id) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        User user = userRepository.getByIdAndRolesEquals(id,roleUser);
        return user;
    }

    @Override
    public User findTrainerByEmail(String email) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        User user = userRepository.findByEmailAndRolesEquals(email,roleUser);
        return user;
    }

    @Override
    public ArrayList<User> findAllTrainer() {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        List<User> user = userRepository.findByRolesEquals(roleUser);
        return (ArrayList<User>) user;
    }

    @Override
    public ArrayList<User> findTrainerByFirstName(String firstName) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        List<User> user = userRepository.findByFirstNameAndRolesEquals(firstName,roleUser);
        return (ArrayList<User>) user;
    }

    @Override
    public ArrayList<User> findTrainerByLastName(String lastName) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        List<User> user = userRepository.findByLastNameAndRolesEquals(lastName,roleUser);
        return (ArrayList<User>) user;
    }

    @Override
    public ArrayList<User> findTrainerByFirstNameAndLastName(String firstName, String lastName) {
        Role roleUser = roleRepository.findByName("ROLE_TRAINER");
        List<User> user = userRepository.findByFirstNameAndLastNameAndRolesEquals(firstName,lastName,roleUser);
        return (ArrayList<User>) user;
    }
}