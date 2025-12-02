package br.com.vandre.springsecurity.config;


import br.com.vandre.springsecurity.entities.Role;
import br.com.vandre.springsecurity.entities.User;
import br.com.vandre.springsecurity.repository.RoleRepository;
import br.com.vandre.springsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin user already exists"),
                () -> {
                    var adminUser = new User();
                    adminUser.setUsername("admin");
                    adminUser.setPassword(passwordEncoder.encode("123"));
                    adminUser.setRoles(Set.of(roleAdmin));

                    userRepository.save(adminUser);

                    System.out.println("Admin user created");
                }
        );

    }
}
