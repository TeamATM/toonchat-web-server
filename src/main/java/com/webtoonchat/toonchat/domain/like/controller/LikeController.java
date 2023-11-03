package com.webtoonchat.toonchat.domain.like.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.like.service.LikeService;
import com.webtoonchat.toonchat.domain.member.entity.Member;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;
	private final MemberService memberService;

	@PostMapping("/{postId}")
	public ResponseEntity addLike(@PathVariable("postId")@Positive Long postId, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		Member member = memberService.findByMemberId(userId);
		likeService.addLike(postId, member);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
