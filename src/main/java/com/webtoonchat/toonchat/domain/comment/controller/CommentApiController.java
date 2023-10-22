package com.webtoonchat.toonchat.domain.comment.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.comment.dto.CommentRequestDto;
import com.webtoonchat.toonchat.domain.comment.service.CommentService;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;


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
}
