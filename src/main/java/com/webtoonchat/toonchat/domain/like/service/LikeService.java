package com.webtoonchat.toonchat.domain.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.board.service.BoardService;
import com.webtoonchat.toonchat.domain.like.entity.Like;
import com.webtoonchat.toonchat.domain.like.repository.LikeRepository;
import com.webtoonchat.toonchat.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
	private final BoardService boardService;
	private final LikeRepository likeRepository;

	public void addLike(Long boardId, Member member) {
		Board board = boardService.findById(boardId);
		if (!likeRepository.existsByMemberAndBoard(member, board)) {
			board.setLikeCount(board.getLikeCount() + 1);
			likeRepository.save(new Like(member, board));
		} else {
			board.setLikeCount(board.getLikeCount() - 1);
			likeRepository.deleteByMemberAndBoard(member, board);
		}
	}
}
