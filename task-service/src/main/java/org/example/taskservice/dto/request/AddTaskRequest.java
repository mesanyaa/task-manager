package org.example.taskservice.dto.request;

import org.example.taskservice.entity.Priority;

import java.time.LocalDateTime;

public record AddTaskRequest(String name, String description, Priority priority, LocalDateTime deadline, String email, int boardId) {
}
