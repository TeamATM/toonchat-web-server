package com.webtoonchat.toonchat.domain.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webtoonchat.toonchat.domain.board.entity.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {
	@Query("select b "
			+ "from Board b left join fetch b.comments c left join fetch c.member "
			+ "where b.characterId=:characterId order by b.id desc")
	List<Board> findAllByCharacterIdOrderByIdDesc(Long characterId);
}
