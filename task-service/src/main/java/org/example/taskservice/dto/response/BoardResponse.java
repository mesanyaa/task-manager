package org.example.taskservice.dto.response;

import org.example.taskservice.dto.TaskDTO;

import java.util.List;

public record BoardResponse(int id, String name, List<TaskDTO> tasks) {
}
