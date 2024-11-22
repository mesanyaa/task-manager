package org.example.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.dto.response.UserDataResponse;
import org.example.userservice.dto.response.UserResponse;
import org.example.userservice.security.JwtService;
import org.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final static String TOKEN = "Authorization";


    @GetMapping
    public UserDTO getUserData(@RequestHeader(TOKEN) String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        log.debug("Received token: {}", token);
        String email = jwtService.extractUsername(token);
        log.debug("Extracted email: {}", email);
        return userService.getUser(email);
    }

    @GetMapping("/{user_id}")
    UserDTO getUserData(@RequestHeader(TOKEN) String token, @PathVariable("user_id") UUID user_id){
        return userService.getUser(user_id);
    }

    @GetMapping("/id")
    public UUID getUserId(@RequestHeader(TOKEN) String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        log.debug("Received token: {}", token);
        String email = jwtService.extractUsername(token);
        log.debug("Extracted email: {}", email);
        return userService.getUserId(email);
    }

    @GetMapping("/user/{email}")
    public UUID getUserEmail(@RequestHeader(TOKEN) String authorizationHeader, @PathVariable("email") String email) {
        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
        return userService.getUserId(decodedEmail);
    }

    @GetMapping("/all")
    public List<UserDataResponse> getAllUsersData() {
        return userService.getAllUsersData();
    }
}
