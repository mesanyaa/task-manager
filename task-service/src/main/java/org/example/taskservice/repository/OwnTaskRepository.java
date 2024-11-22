package org.example.taskservice.repository;

import org.example.taskservice.entity.OwnBoard;
import org.example.taskservice.entity.OwnTask;
import org.example.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OwnTaskRepository extends JpaRepository<OwnTask, Integer> {
}
