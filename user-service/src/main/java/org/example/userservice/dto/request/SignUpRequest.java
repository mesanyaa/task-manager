package org.example.userservice.dto.request;

public record SignUpRequest(String name, String surname, String email, String password) {
}
