package com.webtoonchat.toonchat.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.like.entity.Like;
import com.webtoonchat.toonchat.domain.member.entity.Member;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	//있는지 없는지 검토
	boolean existsByMemberAndBoard(Member member, Board board);

	//삭제
	void deleteByMemberAndBoard(Member member, Board board);

}

