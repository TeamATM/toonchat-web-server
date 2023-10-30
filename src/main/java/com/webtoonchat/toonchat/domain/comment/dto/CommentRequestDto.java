package com.webtoonchat.toonchat.domain.comment.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.comment.entity.Comment;
import com.webtoonchat.toonchat.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
	private Long id;
	private String comment;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	private Member member;
	private Board board;

	/* Dto -> Entity */
	public Comment toEntity() {
		Comment comments = Comment.builder().id(id)
							.comment(comment)
							.createdAt(createdAt)
							.updatedAt(updatedAt)
							.member(member)
							.board(board)
							.build();
		return comments;
	}
}
