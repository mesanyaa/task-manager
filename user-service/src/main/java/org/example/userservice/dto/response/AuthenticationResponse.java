package org.example.userservice.dto.response;

public record AuthenticationResponse(String accessToken, String refreshToken) {
}
