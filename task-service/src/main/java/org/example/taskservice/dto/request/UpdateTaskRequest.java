package org.example.taskservice.dto.request;

import java.time.LocalDateTime;

public record UpdateTaskRequest(String name, String description, LocalDateTime deadline) {
}
