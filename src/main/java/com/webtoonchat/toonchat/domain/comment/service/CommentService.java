package com.webtoonchat.toonchat.domain.comment.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.board.repository.BoardRepository;
import com.webtoonchat.toonchat.domain.comment.dto.CommentRequestDto;
import com.webtoonchat.toonchat.domain.comment.dto.CommentUpdateDto;
import com.webtoonchat.toonchat.domain.comment.entity.Comment;
import com.webtoonchat.toonchat.domain.comment.repository.CommentRepository;
import com.webtoonchat.toonchat.domain.member.entity.Member;
import com.webtoonchat.toonchat.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;

	/* CREATE */
	@Transactional
	public Long commentSave(Long userId, Long articleId, CommentRequestDto dto) {
		Member member = memberRepository.findById(userId).orElseThrow(() ->
				new IllegalArgumentException("댓글 쓰기 실패: 해당 유저가 존재하지 않습니다." + userId));
		Board board = boardRepository.findById(articleId).orElseThrow(() ->
				new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + articleId));
		dto.setMember(member);
		dto.setBoard(board);
		Comment comment = dto.toEntity();
		commentRepository.save(comment);

		return dto.getId();
	}

	@Transactional(readOnly = true)
	public List<Comment> getCommentsByArticleId(Long articleId) {
		return commentRepository.findAllByBoardId(articleId);
	}

	@Transactional
	public Long updateComment(Long commentId, CommentUpdateDto dto) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
				new IllegalArgumentException("댓글 수정 실패: 해당 댓글이 존재하지 않습니다." + commentId));
		comment.setComment(dto.getComment());
		return comment.getId();
	}

	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
				new IllegalArgumentException("댓글 삭제 실패: 해당 댓글이 존재하지 않습니다." + commentId));
		commentRepository.delete(comment);
	}

	public Comment findById(long id) {
		return commentRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("해당 댓글이 존재하지 않습니다."));
	}
}
