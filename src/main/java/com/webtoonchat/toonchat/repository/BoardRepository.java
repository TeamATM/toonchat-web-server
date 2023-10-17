package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.board.Board;



public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findAllByCharacterId(Long characterId);
}
