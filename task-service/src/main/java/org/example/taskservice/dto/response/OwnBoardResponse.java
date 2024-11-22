package org.example.taskservice.dto.response;

import org.example.taskservice.dto.TaskDTO;

import java.util.List;

public record OwnBoardResponse(String name, List<TaskDTO> tasks){
}
