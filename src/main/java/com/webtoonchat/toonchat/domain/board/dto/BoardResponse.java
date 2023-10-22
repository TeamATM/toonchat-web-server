package com.webtoonchat.toonchat.domain.board.dto;

import java.time.LocalDateTime;

import com.webtoonchat.toonchat.domain.board.entity.Board;

import lombok.Getter;


@Getter
public class BoardResponse {
	private final Long id;
	private final Long writerId;
	private final String writerName;
	private final String title;
	private final String content;
	private final Long characterId;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public BoardResponse(Board article) {
		this.id = article.getId();
		this.writerId = article.getWriterId();
		this.writerName = article.getWriter();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
		this.characterId = article.getCharacterId();
		this.updatedAt = article.getUpdatedAt();
	}
}
