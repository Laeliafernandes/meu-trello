package com.meu_trello.demo.service;

import com.meu_trello.demo.domain.model.Lista;
import com.meu_trello.demo.repository.ListaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ListaService {
    private final ListaRepository listaRepository;

    public ListaService(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
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
    public void delete(Long id) {
        if (listaRepository.existsById(id)) {
            listaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This Lists doesn't exists.");
        }

    }
}
