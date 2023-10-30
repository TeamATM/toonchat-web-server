package com.webtoonchat.toonchat.domain.comment.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.webtoonchat.toonchat.domain.comment.entity.Comment;

import lombok.Getter;
@Getter
public class CommentResponseDto {
	private Long id;
	private String comment;
	private LocalDateTime  createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	private String nickname;
	private Long boardId;

	/* Entity -> Dto*/
	public CommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.comment = comment.getComment();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
		this.nickname = comment.getMember().getName();
		this.boardId = comment.getBoard().getId();
	}
}
