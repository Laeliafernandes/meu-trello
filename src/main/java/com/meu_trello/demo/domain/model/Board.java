package com.meu_trello.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_board")
@Data
public class Board {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "id")
    private List<Lista> lists = new ArrayList<>();
}
