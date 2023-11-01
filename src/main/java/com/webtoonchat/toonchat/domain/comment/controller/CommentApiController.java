package com.webtoonchat.toonchat.domain.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.comment.dto.CommentRequestDto;
import com.webtoonchat.toonchat.domain.comment.dto.CommentResponseDto;
import com.webtoonchat.toonchat.domain.comment.dto.CommentUpdateDto;
import com.webtoonchat.toonchat.domain.comment.service.CommentService;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
	private final CommentService commentService;
	private final MemberService memberService;

	@PostMapping("/boards/{characterId}/{articleId}/comments")
	public ResponseEntity commentSave(
			@PathVariable Long articleId, @RequestBody CommentRequestDto dto, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		return ResponseEntity.ok(commentService.commentSave(userId, articleId, dto));
	}

	@GetMapping("/boards/{characterId}/{articleId}/comments")
	public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long articleId) {
		List<CommentResponseDto> comments = commentService.getCommentsByArticleId(articleId)
				.stream()
				.map(CommentResponseDto::new)
				.toList();;
		return ResponseEntity.ok(comments);
	}

	@PutMapping("/boards/{characterId}/{articleId}/comments/{commentId}")
	public ResponseEntity<Long> updateComment(
			@PathVariable Long commentId, @RequestBody CommentUpdateDto dto, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		if (!commentService.findById(commentId).getMember().getId().equals(userId)) {
			log.error("댓글 작성자가 아닌 댓글 수정 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		Long updatedCommentId = commentService.updateComment(commentId, dto);
		return ResponseEntity.ok(updatedCommentId);
	}

	@DeleteMapping("/boards/{characterId}/{articleId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(
			@PathVariable Long commentId, @PathVariable Long articleId, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		if (!commentService.findById(commentId).getMember().getId().equals(userId)) {
			log.error("댓글 작성자가 아닌 댓글 삭제 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		commentService.deleteComment(articleId, commentId);
		return ResponseEntity.noContent().build();
	}
}
