package com.webtoonchat.toonchat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.stream.events.Characters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.domain.FriendshipId;
import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.service.CharacterService;
import com.webtoonchat.toonchat.service.FriendshipService;
import com.webtoonchat.toonchat.service.MemberService;

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
	public ResponseEntity<List<Character>> getAll() {
		// 현재 사용자 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Claims claims = (Claims) principal;
		Long userId = ((Integer) claims.get("userId")).longValue();

		// Friendship 테이블에서 characterId 목록 가져오기
		List<Character> friends = friendshipService.getFriendsByMemberId(userId);

		return ResponseEntity.ok(friends);
	}


	// @GetMapping("/{id}")
	// public ResponseEntity getById(@PathVariable Long id) {
	// 	Optional<Friendship> optionalFriendship = friendshipService.getById(id);
	//
	// 	if (optionalFriendship.isPresent()) {
	// 		return new ResponseEntity(optionalFriendship.get(), HttpStatus.OK);
	// 	} else {
	// 		return new ResponseEntity(HttpStatus.NOT_FOUND);
	// 	}
	// }

	@Operation(description = "새로운 친구관계 생성")
	@PostMapping("/{characterId}")
	public ResponseEntity createFriendship(@PathVariable Long characterId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Claims claims = (Claims) principal;
		Long userid = ((Integer) claims.get("userId")).longValue();

		friendshipService.createFriendship(userid, characterId);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@DeleteMapping("/{characterId}")
	public ResponseEntity delete(@PathVariable Long characterId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Claims claims = (Claims) principal;
		Long userId = ((Integer) claims.get("userId")).longValue();

		friendshipService.delete(userId, characterId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
