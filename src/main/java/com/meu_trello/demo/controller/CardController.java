package com.meu_trello.demo.controller;

import com.meu_trello.demo.controller.record.IDRequest;
import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.service.CardService;
import com.meu_trello.demo.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    private final ListaService listaService;
    public CardController(CardService cardService, ListaService listaService) {
        this.cardService = cardService;
        this.listaService = listaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        var card = cardService.findById(id);
        return ResponseEntity.ok().body(card);
    }
/*
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
*/
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cardId}/to/{listaId}")
    @Operation(
            summary = "Move um determinado card entre duas Listas de um determinado Board.",
            description = ""
    )
    public Card moveCardToList(
            @PathVariable Long cardId,
            @PathVariable Long listaId) {

        Lista lista = listaService.findById(listaId);
        Card card = cardService.findById(cardId);

        card.setLista(lista);
        return cardService.update(card);

    }
}