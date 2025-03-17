package com.meu_trello.demo.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_lista")
@Data
public class Lista {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "id")
    private List<Card> cards = new ArrayList<>();

    @ManyToOne
    private Board board;
}
