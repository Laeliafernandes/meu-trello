package com.meu_trello.demo.controller;

import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.Files;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        var card = cardService.findById(id);
        return ResponseEntity.ok().body(card);
    }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card userTocard) {
        var cardCreated = cardService.create(userTocard);
        URI Location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .path("/{id}")
                .buildAndExpand(cardCreated.getId())
                .toUri();
        return ResponseEntity.created(Location).body(cardCreated);
    }

    @PutMapping
    public ResponseEntity<Card> update(@RequestBody Card userTocard) {
        var cardUpdated = cardService.update(userTocard);
        return ResponseEntity.ok().body(cardUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCard(@RequestBody Card cardToDelete) {
        deleteCard(cardToDelete);
        return ResponseEntity.noContent().build();
    }

}