package org.example.taskservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.request.UpdateTaskPriorityRequest;
import org.example.taskservice.entity.OwnBoard;
import org.example.taskservice.entity.OwnTask;
import org.example.taskservice.entity.Task;
import org.example.taskservice.repository.OwnTaskRepository;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnTaskService {

    private final OwnTaskRepository ownTaskRepository;

    public OwnTask updatePriority(UpdateTaskPriorityRequest updateTaskPriorityRequest) {
        OwnTask ownTask = ownTaskRepository.findById(updateTaskPriorityRequest.taskId()).orElseThrow();
        ownTask.setPriority(updateTaskPriorityRequest.priority());
        return ownTaskRepository.save(ownTask);
    }

    public OwnTask findTaskById(int id) {
        return ownTaskRepository.findById(id).orElse(null);
    }

    public OwnTask updateTask(OwnTask ownTask) {
        return ownTaskRepository.save(ownTask);
    }

    public OwnTask saveTask(OwnTask ownTask) {
        return ownTaskRepository.save(ownTask);
    }

    public void deleteTaskById(int id) {
        ownTaskRepository.deleteById(id);
    }
}
