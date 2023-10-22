package com.webtoonchat.toonchat.domain.member.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.character.dto.CharacterResponseDto;
import com.webtoonchat.toonchat.domain.character.service.CharacterService;
import com.webtoonchat.toonchat.domain.member.service.FriendshipService;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendshipController {

	private final FriendshipService friendshipService;
	private final MemberService memberService;
	private final CharacterService characterService;

	@GetMapping("")
	public ResponseEntity<List<CharacterResponseDto>> getAll(@Login Claims claims) {
		// 현재 사용자 정보 가져오기
		Long userId = claims.get("userId", Long.class);

		// Friendship 테이블에서 characterId 목록 가져오기
		List<CharacterResponseDto> friends = friendshipService.getFriendsByMemberId(userId);

		return ResponseEntity.ok(friends);
	}

	@Operation(description = "새로운 친구관계 생성")
	@PostMapping("/{characterId}")
	public ResponseEntity createFriendship(@PathVariable Long characterId, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);

		friendshipService.createFriendship(userId, characterId);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@DeleteMapping("/{characterId}")
	public ResponseEntity delete(@PathVariable Long characterId, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);

		friendshipService.delete(userId, characterId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
