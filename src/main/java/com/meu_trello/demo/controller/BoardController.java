package com.meu_trello.demo.controller;

import com.meu_trello.demo.domain.model.Board;
import com.meu_trello.demo.repository.BoardRepository;
import com.meu_trello.demo.service.BoardService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        var board = boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board boardToCreate) {
        var boardCreated = boardService.create(boardToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardCreated.getId())
                .toUri();
        return ResponseEntity.ok(boardCreated);
    }

    @PutMapping
    public ResponseEntity<Board> updateBoard(@RequestBody Board boardToUpdate) {
        var boardUpdated = boardService.update(boardToUpdate);
        return ResponseEntity.ok(boardUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBoard(@RequestBody Board boardToDelete) {
        boardService.delete(boardToDelete);
        return ResponseEntity.noContent().build();
    }
}
