package org.example.taskservice.client;

import org.example.taskservice.config.FeignConfig;
import org.example.taskservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "user-service", url = "http://user-service:8081/api/v1/user", configuration = FeignConfig.class)
public interface UserClient {
    static final String TOKEN = "Authorization";

    @GetMapping("/id")
    UUID getUserByToken(@RequestHeader(TOKEN) String token);

    @GetMapping
    UserDTO getUserData(@RequestHeader(TOKEN) String token);

    @GetMapping("/{user_id}")
    UserDTO getUserData(@RequestHeader(TOKEN) String token, @PathVariable("user_id") UUID user_id);

    @GetMapping("/user/{email}")
    UUID getUserEmail(@RequestHeader(TOKEN) String authorizationHeader, @PathVariable("email") String email);
}
