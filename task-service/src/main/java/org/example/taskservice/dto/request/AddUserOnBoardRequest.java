package org.example.taskservice.dto.request;

import java.util.UUID;

public record AddUserOnBoardRequest(int boardId, UUID userId) {
}
