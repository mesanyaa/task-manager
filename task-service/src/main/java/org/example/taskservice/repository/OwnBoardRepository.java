package org.example.taskservice.repository;

import org.example.taskservice.entity.OwnBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OwnBoardRepository extends JpaRepository<OwnBoard, Integer> {
    @Query("SELECT o.id FROM OwnBoard o WHERE o.ownerId = :ownerId")
    int findIdByOwnerId(@Param("ownerId") UUID ownerId);
    OwnBoard findByOwnerId(UUID ownerId);
    boolean existsByOwnerId(UUID ownerId);
}
