package com.meu_trello.demo.service;

import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.repository.CardRepository;
import com.meu_trello.demo.repository.ListaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class CardService {

    private final CardRepository cardRepository;
    private final ListaRepository listaRepository;

    public CardService(CardRepository cardRepository, ListaRepository listaRepository) {
        this.cardRepository = cardRepository;
        this.listaRepository = listaRepository;
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card update(Card card) {
        if (cardRepository.existsById(card.getId())) {
            return cardRepository.save(card);
        } else {
            throw new IllegalArgumentException("This Card doesn't exists.");
        }
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElse(null);
        Lista lista = card.getLista();
        lista.getCards().remove(card);
        listaRepository.save(lista);
    }

}
