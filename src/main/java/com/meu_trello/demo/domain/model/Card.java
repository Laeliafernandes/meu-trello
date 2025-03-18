package com.meu_trello.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_card")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "lista_id")
    @JsonIgnore
    private Lista lista;

    public Card() {}

    public Card(String nome) {
        this.nome = nome;
    }
}

