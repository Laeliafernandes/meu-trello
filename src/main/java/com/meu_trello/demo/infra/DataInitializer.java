package com.meu_trello.demo.infra;

import com.meu_trello.demo.domain.model.Board;
import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer {
    private final BoardRepository boardRepository;

    public DataInitializer(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeDatabase() {

            if (boardRepository.count() == 0) {
                Board board = new Board("Board 1");
                Lista lista1 = new Lista("Lista de Exemplo 1");
                Lista lista2 = new Lista("Lista de Exemplo 2");

                lista1.setBoard(board);
                lista2.setBoard(board);

                // Criação dos Cards e associando à Lista
                Card card1 = new Card("Card 1 de Exemplo");
                Card card2 = new Card("Card 2 de Exemplo");
                card1.setLista(lista1);
                card2.setLista(lista1);

                // Adicionando os Cards à Lista
                lista1.getCards().add(card1);
                lista1.getCards().add(card2);

                // Adicionando a Lista ao Board
                board.getLists().add(lista1);
                board.getLists().add(lista2);
                boardRepository.save(board);
            }

    }
}