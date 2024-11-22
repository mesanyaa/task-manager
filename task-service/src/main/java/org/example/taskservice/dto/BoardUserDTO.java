package org.example.taskservice.dto;

import java.util.UUID;

public record BoardUserDTO(int boardId, UUID userId) {
}
