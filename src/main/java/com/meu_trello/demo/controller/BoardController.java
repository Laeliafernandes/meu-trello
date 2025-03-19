package com.meu_trello.demo.controller;

import com.meu_trello.demo.controller.record.IDRequest;
import com.meu_trello.demo.controller.record.NomeRequest;
import com.meu_trello.demo.domain.model.Board;
import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.service.BoardService;

import com.meu_trello.demo.service.CardService;
import com.meu_trello.demo.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final ListaService listaService;

    private final CardService cardService;

    public BoardController(BoardService boardService,
                           ListaService listaService,
                           CardService cardService) {
        this.boardService = boardService;
        this.listaService = listaService;
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Pega os dados de um determinado board.",
            description = ""
    )
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/{boardId}/lists/{listaId}/card")
    @Operation(
            summary = "Cria um card em uma determinada lista de um determinado board",
            description = ""
    )
    public Card addCardToList(
            @PathVariable Long boardId,
            @PathVariable Long listaId,
            @RequestBody Card card) {

        // Recupera o Board e a Lista pelo ID.
        Board board = boardService.findById(boardId);
        Lista lista = listaService.findById(listaId);
        // Define a Lista para o Card.
        card.setLista(lista);

        // Adiciona o Card à Lista.
        lista.getCards().add(card);

        // Salva o Card e a Lista.
        cardService.create(card);
        listaService.create(lista);

        // Retorna o Card adicionado.
        return card;
    }

    @PostMapping("/{boardId}/lists")
    @Operation(
            summary = "Cria uma nova lista para um determinado board",
            description = ""
    )
    public Lista addListaToBoard(@PathVariable Long boardId, @RequestBody NomeRequest body) {

        String nome = body.nome();

        // Recupera o Board pelo ID
        Board board = boardService.findById(boardId);
        // Define o Board da Lista (associando a nova Lista ao Board)
        Lista lista = new Lista(nome);
        lista.setBoard(board);

        // Salva a nova Lista associada ao Board
        listaService.create(lista);

        // Retorna a nova Lista adicionada
        return lista;
    }

    @PutMapping("/{boardId}/lists/{listaId}")
    @Operation(
            summary = "Altera o nome de uma determinada lista de um determinado board",
            description = ""
    )
    public Lista updateListaName(@PathVariable Long boardId,
                                 @PathVariable Long listaId,
                                 @RequestBody NomeRequest body) {

        String nome = body.nome();

        // Recupera o Board pelo ID.
        Board board = boardService.findById(boardId);
        // Recupera a Lista pelo ID.
        Lista lista = listaService.findById(listaId);

        // Verifica se a Lista pertence ao Board especificado.
        if (!lista.getBoard().getId().equals(boardId)) {
            throw new RuntimeException("A lista não pertence ao Board.");
        }

        // Atualiza o nome da Lista.
        lista.setNome(nome);

        // Salva a Lista atualizada.
        return listaService.create(lista);
    }

    @PutMapping("/{boardId}")
    @Operation(
            summary = "Altera o nome de um determinado board",
            description = ""
    )
    public Board updateBoardName(@PathVariable Long boardId,
                                 @RequestBody NomeRequest body) {

        String nome = body.nome();

        // Recupera o Board pelo ID.
        Board board = boardService.findById(boardId);

        board.setNome(nome);
        return boardService.update(board);
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
/*
    @PutMapping
    public ResponseEntity<Board> updateBoard(@RequestBody Board boardToUpdate) {
        var boardUpdated = boardService.update(boardToUpdate);
        return ResponseEntity.ok(boardUpdated);
    }
*/
    @DeleteMapping
    public ResponseEntity<Void> deleteBoard(@RequestBody IDRequest body) {
        boardService.delete(body.id());
        return ResponseEntity.noContent().build();
    }
}
