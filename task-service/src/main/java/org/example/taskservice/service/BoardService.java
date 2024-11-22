package org.example.taskservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.BoardDTO;
import org.example.taskservice.dto.response.BoardInfoResponse;
import org.example.taskservice.entity.Board;
import org.example.taskservice.entity.Task;
import org.example.taskservice.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Task> findTasksByBoardId(int boardId){
        return boardRepository.findById(boardId).get().getTasks();
    }

    public void deleteBoard(int boardId){
        boardRepository.deleteById(boardId);
    }

    public Board addBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setName(boardDTO.name());
        board.setOwnerId(boardDTO.ownerId());
        return boardRepository.save(board);
    }

    public Board getByBoardId(int boardId) {
        return boardRepository.findById(boardId).get();
    }

    public String getNameById(int boardId){
        return boardRepository.findById(boardId).get().getName();
    }
}