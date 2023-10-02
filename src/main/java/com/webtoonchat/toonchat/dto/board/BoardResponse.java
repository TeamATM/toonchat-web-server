package com.webtoonchat.toonchat.dto.board;

import com.webtoonchat.toonchat.domain.board.Board;

import lombok.Getter;

@Getter
public class BoardResponse {
	private final String title;
	private final String content;

	public BoardResponse(Board article) {
		this.title = article.getTitle();
		this.content = article.getContent();
	}
}
