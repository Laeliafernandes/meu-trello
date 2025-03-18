package com.meu_trello.demo.controller;

import com.meu_trello.demo.controller.record.IDRequest;
import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.service.ListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/lista")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista> findById(@PathVariable Long id) {
        var lista = listaService.findById(id);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLista(@PathVariable Long id) {
        listaService.deleteLista(id);
        return ResponseEntity.noContent().build();
    }

}

