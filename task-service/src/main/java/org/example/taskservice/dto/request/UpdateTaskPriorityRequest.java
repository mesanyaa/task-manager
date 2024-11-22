package org.example.taskservice.dto.request;

import org.example.taskservice.entity.Priority;

public record UpdateTaskPriorityRequest (int taskId, Priority priority){
}
