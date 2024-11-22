package org.example.taskservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.client.UserClient;
import org.example.taskservice.dto.request.AddTaskRequest;
import org.example.taskservice.dto.request.UpdateTaskPriorityRequest;
import org.example.taskservice.dto.request.UpdateTaskRequest;
import org.example.taskservice.entity.OwnBoard;
import org.example.taskservice.entity.OwnTask;
import org.example.taskservice.entity.Task;
import org.example.taskservice.service.OwnBoardService;
import org.example.taskservice.service.OwnTaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/own-task")
@RequiredArgsConstructor
@Slf4j
public class OwnTaskController {
    private final UserClient userClient;
    private final OwnTaskService ownTaskService;
    private final OwnBoardService ownBoardService;
    private final static String TOKEN = "Authorization";

    @PostMapping("update-priority")
    public OwnTask getUserTasks(@RequestHeader(TOKEN) String token,
                                @RequestBody UpdateTaskPriorityRequest updateTaskPriorityRequest) {
        return ownTaskService.updatePriority(updateTaskPriorityRequest);
    }

    @PostMapping("/add")
    public OwnTask createBoard(@RequestHeader(TOKEN) String token, @RequestBody AddTaskRequest request) {
        OwnTask ownTask = new OwnTask();
        UUID user_id = userClient.getUserByToken(token);
        OwnBoard ownBoard = ownBoardService.findByOwnerId(user_id);
        ownTask.setCreatedAt(LocalDateTime.now());
        ownTask.setOwnBoard(ownBoard);
        ownTask.setName(request.name());
        ownTask.setPriority(request.priority());
        ownTask.setDescription(request.description());
        ownTask.setTimer(request.deadline());
        ownTaskService.saveTask(ownTask);

        return ownTask;
    }

    @PutMapping("/update/{taskId}")
    public OwnTask updateTask(@RequestHeader(TOKEN) String token, @PathVariable("taskId") int taskId, @RequestBody UpdateTaskRequest updateTaskRequest) {
        OwnTask ownTask = ownTaskService.findTaskById(taskId);

        ownTask.setName(updateTaskRequest.name());
        ownTask.setDescription(updateTaskRequest.description());
        ownTask.setTimer(updateTaskRequest.deadline());

        ownTaskService.updateTask(ownTask);
        return ownTask;
    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteTask(@RequestHeader(TOKEN) String token, @PathVariable("taskId") int taskId){
        ownTaskService.deleteTaskById(taskId);
    }
}
