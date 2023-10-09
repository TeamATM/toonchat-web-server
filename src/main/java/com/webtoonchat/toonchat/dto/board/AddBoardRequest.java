package com.webtoonchat.toonchat.dto.board;

import java.time.LocalDateTime;

import com.webtoonchat.toonchat.domain.board.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddBoardRequest {
	private int writerId;
	private String title;
	private String content;
	private String bgno;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Board toEntity() {
		return Board.builder()
				.writerId(writerId)
				.title(title)
				.content(content)
				.bgno(bgno)
				.createdAt(createdAt)
				.updatedAt(updatedAt)
				.build();
	}
}
