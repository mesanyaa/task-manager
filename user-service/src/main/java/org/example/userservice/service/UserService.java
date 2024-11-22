package org.example.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.dto.response.UserDataResponse;
import org.example.userservice.dto.response.UserResponse;
import org.example.userservice.entity.User;
import org.example.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        log.info("Пользователь {} получен", user.getEmail());
        return new UserDTO(user.getName(), user.getSurname(), user.getEmail());
    }

    @Transactional
    public UserDTO getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserDTO(user.getName(), user.getSurname(), user.getEmail());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public UUID getUserId(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")).getId();
    }
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public List<UserDataResponse> getAllUsersData() {
        log.info(userRepository.findAll().toString());
        return userRepository.findAll()
                .stream()
                .map(this::convertToUserDataResponse)
                .collect(Collectors.toList());
    }

    private UserDataResponse convertToUserDataResponse(User user) {
        return new UserDataResponse(user.getId(), user.getEmail());
    }
}
