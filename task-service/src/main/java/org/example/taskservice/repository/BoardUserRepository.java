package org.example.taskservice.repository;

import org.example.taskservice.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BoardUserRepository extends JpaRepository<BoardUser, Integer> {
    List<BoardUser> findByUserId(UUID userId);
    @Query("SELECT bu.userId FROM BoardUser bu WHERE bu.boardId = :boardId")
    List<UUID> findUserIdsByBoardId(int boardId);
    Boolean existsByBoardIdAndUserId(int boardId, UUID userId);
}
