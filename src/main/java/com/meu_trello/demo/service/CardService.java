package com.meu_trello.demo.service;

import com.meu_trello.demo.domain.model.Card;
import com.meu_trello.demo.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
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

    public void delete(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This Card doesn't exists.");
        }
    }

}
