package org.example.taskservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.client.UserClient;
import org.example.taskservice.dto.BoardDTO;
import org.example.taskservice.dto.BoardUserDTO;
import org.example.taskservice.dto.TaskDTO;
import org.example.taskservice.dto.UserDTO;
import org.example.taskservice.dto.request.AddBoardRequest;
import org.example.taskservice.dto.response.BoardResponse;
import org.example.taskservice.entity.Board;
import org.example.taskservice.entity.TaskType;
import org.example.taskservice.service.BoardService;
import org.example.taskservice.service.BoardUserService;
import org.example.taskservice.service.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final UserClient userClient;
    private final BoardService boardService;
    private final TaskService taskService;
    public final BoardUserService boardUserService;
    private final static String TOKEN = "Authorization";

    @GetMapping("/{id}/tasks")
    public BoardResponse getTasks(@RequestHeader(TOKEN) String token, @PathVariable("id") int id) {
        return new BoardResponse(id, boardService.getNameById(id),
                boardService.findTasksByBoardId(id).stream()
                        .map(task -> new TaskDTO(task.getId(), task.getName(), task.getPriority(),
                                task.getTimer(), initials(token, task.getUserId()), task.getDescription(), TaskType.TASK))
                        .toList());
    }
    @DeleteMapping("/{id}")
    public void deleteBoard(@RequestHeader(TOKEN) String token, @PathVariable("id") int id) {{
        boardService.deleteBoard(id);
    }}

    @PostMapping("/add")
    public BoardDTO createBoard(@RequestHeader(TOKEN) String token, @RequestBody AddBoardRequest request) {
        UUID user_id = userClient.getUserByToken(token);
        BoardDTO boardDTO = new BoardDTO(request.name(), user_id);
        Board board = boardService.addBoard(boardDTO);
        BoardUserDTO boardUserDTO = new BoardUserDTO(board.getId(), user_id);
        boardUserService.save(boardUserDTO);
        return boardDTO;
    }

    private String initials(String token, UUID user_id) {
        if (user_id == null){
            return "N/A";
        }
        UserDTO userDTO  = userClient.getUserData(token, user_id);
        return userDTO.surname().charAt(0) + "" + userDTO.name().charAt(0);
    }
}
