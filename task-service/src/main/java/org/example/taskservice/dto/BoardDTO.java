package org.example.taskservice.dto;

import java.util.UUID;

public record BoardDTO(String name, UUID ownerId) {}
