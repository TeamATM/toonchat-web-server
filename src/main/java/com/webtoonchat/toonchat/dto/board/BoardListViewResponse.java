package com.webtoonchat.toonchat.dto.board;

import com.webtoonchat.toonchat.domain.board.Board;

import lombok.Getter;

@Getter
public class BoardListViewResponse {
	private final long id;
	private final String title;
	private final String content;

	public BoardListViewResponse(Board article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.content = article.getContent();
	}
}
