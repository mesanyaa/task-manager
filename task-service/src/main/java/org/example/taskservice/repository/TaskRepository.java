package org.example.taskservice.repository;

import org.example.taskservice.entity.Board;
import org.example.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTasksByUserId(UUID user_id);
}
