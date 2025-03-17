package com.meu_trello.demo.repository;

import com.meu_trello.demo.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository  extends JpaRepository<Board, Long> {
}
