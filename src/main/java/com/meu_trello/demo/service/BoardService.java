package com.meu_trello.demo.service;

import com.meu_trello.demo.domain.model.Board;
import com.meu_trello.demo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public Board update(Board board) {
        if (boardRepository.existsById(board.getId())) {
            return boardRepository.save(board);
        } else {
            throw new IllegalArgumentException("This Board doesn't exists.");
        }
    }

    public void delete(Board board) {
        if (boardRepository.existsById(board.getId())) {
            boardRepository.delete(board);
        } else {
            throw new IllegalArgumentException("This Board doesn't exists.");
        }
    }

}
