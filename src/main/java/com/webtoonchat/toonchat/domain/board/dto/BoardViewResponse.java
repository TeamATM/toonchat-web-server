package com.webtoonchat.toonchat.domain.board.dto;

import java.time.LocalDateTime;

import com.webtoonchat.toonchat.domain.board.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardViewResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;

	public BoardViewResponse(Board article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
	}
}
