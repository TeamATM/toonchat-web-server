package com.webtoonchat.toonchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.board.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
