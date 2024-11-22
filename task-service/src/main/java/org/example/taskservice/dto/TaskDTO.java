package org.example.taskservice.dto;

import org.example.taskservice.entity.Priority;
import org.example.taskservice.entity.TaskType;

import java.time.LocalDateTime;

public record TaskDTO(int id, String name, Priority priority, LocalDateTime timer, String owner, String description, TaskType type) {}
