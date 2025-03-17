package com.meu_trello.demo.controller;

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

    @PostMapping
    public ResponseEntity<Lista> create(@RequestBody Lista lista) {
        var listaCreated = listaService.create(lista);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(listaCreated.getId())
                .toUri();
        return ResponseEntity.ok(listaCreated);
    }

    @PutMapping
    public ResponseEntity<Lista> update(@RequestBody Lista lista) {
        var listaUpdated = listaService.update(lista);
        return ResponseEntity.ok(listaUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Lista> deleteLista(@RequestBody Lista listaToDelete) {
        deleteLista(listaToDelete);
        return ResponseEntity.noContent().build();
    }
}

