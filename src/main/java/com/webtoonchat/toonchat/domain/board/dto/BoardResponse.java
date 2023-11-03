package com.webtoonchat.toonchat.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.comment.dto.CommentResponseDto;
import com.webtoonchat.toonchat.domain.comment.entity.Comment;

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
	private List<CommentResponseDto> comments;
	private Long likeCount;
	private Long commentCount;

	public BoardResponse(Board article) {
		this.id = article.getId();
		this.writerId = article.getWriterId();
		this.writerName = article.getWriter();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
		this.characterId = article.getCharacterId();
		this.updatedAt = article.getUpdatedAt();
		List<Comment>  commentList = article.getComments();
		if (commentList != null) {
			this.comments = commentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());
		}
		this.likeCount = article.getLikeCount();
		this.commentCount = article.getCommentCount();
	}
}
