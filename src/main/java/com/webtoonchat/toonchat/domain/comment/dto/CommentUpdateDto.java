package com.webtoonchat.toonchat.domain.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateDto {
	private String comment; // 댓글 내용

	public CommentUpdateDto(String comment) {
		this.comment = comment;
	}
}
