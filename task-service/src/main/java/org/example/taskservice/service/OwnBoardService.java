package org.example.taskservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.OwnBoardDTO;
import org.example.taskservice.entity.OwnBoard;
import org.example.taskservice.entity.OwnTask;
import org.example.taskservice.repository.OwnBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnBoardService {
    private final OwnBoardRepository ownBoardRepository;

    public List<OwnTask> findTasksByBoardId(int boardId){
        return ownBoardRepository.findById(boardId).get().getOwnTasks();
    }

    public int getIdByUserId(UUID userId){
        return ownBoardRepository.findIdByOwnerId(userId);
    }
    public OwnBoard findByOwnerId(UUID ownerId){
        return ownBoardRepository.findByOwnerId(ownerId);
    }

    public OwnBoard addBoard(OwnBoardDTO ownBoardDTO) {
        OwnBoard ownBoard = new OwnBoard();
        ownBoard.setOwnerId(ownBoardDTO.ownerId());
        return ownBoardRepository.save(ownBoard);
    }

    public boolean existsByOwnerId(UUID ownerId) {
        return ownBoardRepository.existsByOwnerId(ownerId);
    }
}
