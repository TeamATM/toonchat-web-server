package com.webtoonchat.toonchat.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.board.entity.Board;



public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findAllByCharacterId(Long characterId);
}
