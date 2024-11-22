package org.example.userservice.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.entity.Role;
import org.example.userservice.entity.User;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitAdmin implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User adm = User
                    .builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin@adm.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ROLE_ADMIN)
                    .build();
            log.debug("Admin created: {}", adm);
            userService.save(adm);
        }
    }
}