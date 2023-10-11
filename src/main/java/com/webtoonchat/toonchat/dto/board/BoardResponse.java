package com.webtoonchat.toonchat.dto.board;

import java.time.LocalDateTime;

import com.webtoonchat.toonchat.domain.board.Board;

import lombok.Getter;


@Getter
public class BoardResponse {
	private final Long id;
	private final Long writer;
	private final String title;
	private final String content;
	private final String characterId;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public BoardResponse(Board article) {
		this.id = article.getId();
		this.writer = article.getWriterId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
		this.characterId = article.getCharacterId();
	}
}
