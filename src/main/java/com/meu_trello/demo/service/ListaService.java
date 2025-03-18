package com.meu_trello.demo.service;

import com.meu_trello.demo.domain.model.Board;
import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.repository.BoardRepository;
import com.meu_trello.demo.repository.ListaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ListaService {
    private final ListaRepository listaRepository;
    private final BoardRepository boardRepository;

    public ListaService(ListaRepository listaRepository, BoardRepository boardRepository) {
        this.listaRepository = listaRepository;
        this.boardRepository = boardRepository;
    }

    public Lista findById(Long id) {
        return listaRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Lista create(Lista lista) {
        return listaRepository.save(lista);
    }

    public Lista update(Lista lista) {
        if (listaRepository.existsById(lista.getId())) {
            return listaRepository.save(lista);
        } else {
            throw new IllegalArgumentException("This Lists doesn't exists.");
        }
    }

    public void deleteLista(Long id) {
        Lista lista = listaRepository.findById(id).orElse(null);
        Board board = lista.getBoard();
        board.getLists().remove(lista);
        boardRepository.save(board);
    }
}
