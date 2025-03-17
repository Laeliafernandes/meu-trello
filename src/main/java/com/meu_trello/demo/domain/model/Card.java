package com.meu_trello.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "tb_card")
@Data
public class Card {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    @ManyToOne
    private Lista lista;

}

