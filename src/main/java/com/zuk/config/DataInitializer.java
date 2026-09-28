package com.zuk.config;

import com.zuk.model.Role;
import com.zuk.model.Status;
import com.zuk.model.User;
import com.zuk.repository.RoleRepository;
import com.zuk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Makes a fresh database usable: creates the roles registration relies on and, if
 * ADMIN_USERNAME / ADMIN_PASSWORD are set, a first active admin who can activate other users.
 */
@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {

    private static final List<String> ROLES = List.of("ROLE_USER", "ROLE_TRAINER", "ROLE_ADMIN");

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final String adminUsername;
    private final String adminPassword;
    private final String adminEmail;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           @Value("${app.admin.username:}") String adminUsername,
                           @Value("${app.admin.password:}") String adminPassword,
                           @Value("${app.admin.email:admin@localhost}") String adminEmail) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        for (String name : ROLES) {
            if (roleRepository.findByName(name) == null) {
                Role role = new Role();
                role.setName(name);
                role.setStatus(Status.ACTIVE);
                roleRepository.save(role);
                log.info("Created role {}", name);
            }
        }

        if (StringUtils.hasText(adminUsername) && StringUtils.hasText(adminPassword)
                && userRepository.findByUsername(adminUsername) == null) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setStatus(Status.ACTIVE);
            admin.setRoles(new ArrayList<>(List.of(roleRepository.findByName("ROLE_USER"), roleRepository.findByName("ROLE_ADMIN"))));
            userRepository.save(admin);
            log.info("Created admin user {}", adminUsername);
        }
    }
}
