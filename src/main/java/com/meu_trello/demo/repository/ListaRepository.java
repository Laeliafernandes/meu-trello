package com.meu_trello.demo.repository;

import com.meu_trello.demo.domain.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository  extends JpaRepository<Lista, Long> {
}
