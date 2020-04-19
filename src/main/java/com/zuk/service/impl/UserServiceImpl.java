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
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted",id);
    }
}