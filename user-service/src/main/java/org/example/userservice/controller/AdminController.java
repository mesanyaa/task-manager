package org.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.userservice.entity.User;
import org.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

}