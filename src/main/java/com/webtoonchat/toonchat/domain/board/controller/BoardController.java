package com.webtoonchat.toonchat.domain.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.board.dto.AddBoardRequest;
import com.webtoonchat.toonchat.domain.board.dto.BoardResponse;
import com.webtoonchat.toonchat.domain.board.dto.UpdateBoardRequest;
import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.board.service.BoardService;
import com.webtoonchat.toonchat.domain.member.entity.Member;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {
	private final BoardService boardService;
	private final MemberService memberService;
	@Operation(description = "특정 캐릭터 게시판 게시글 작성")
	@PostMapping("/boards/{characterId}")
	public ResponseEntity<Board> addBoard(@PathVariable Long characterId,
		@RequestBody AddBoardRequest request,
		@Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		Member member = memberService.findByMemberId(userId);
		Board savedArticle = boardService.save(request, characterId, member.getName(), userId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}

	@Operation(description = "특정 캐릭터 게시판 모든 게시글 조회")
	@GetMapping("/boards/{characterId}")
	public ResponseEntity<List<BoardResponse>> findAllBoards(@PathVariable Long characterId) {
		List<BoardResponse> articles = boardService.findAllByCharacterId(characterId)
				.stream()
				.map(BoardResponse::new)
				.toList();

		return ResponseEntity.ok()
				.body(articles);
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 조회")
	@GetMapping("/boards/{characterId}/{postId}")
	public ResponseEntity<BoardResponse> findBoard(@PathVariable long postId) {
		Board article = boardService.findById(postId);

		return ResponseEntity.ok()
				.body(new BoardResponse(article));
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 삭제")
	@DeleteMapping("/boards/{characterId}/{postId}")
	public ResponseEntity<Void> deleteBoard(@PathVariable long postId, @Login Claims claims) {
		Long userId = claims.get("userId", Long.class);
		if (!boardService.findById(postId).getWriterId().equals(userId)) {
			log.error("작성자가 아닌 글 삭제 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		boardService.delete(postId);
		return ResponseEntity.ok()
				.build();
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 수정")
	@PutMapping("/boards/{characterId}/{postId}")
	public ResponseEntity<Board> updateBoard(
		@PathVariable long postId,
		@RequestBody UpdateBoardRequest request,
		@Login Claims claims
	) {
		Long userId = claims.get("userId", Long.class);
		if (!boardService.findById(postId).getWriterId().equals(userId)) {
			log.error("작성자가 아닌 글 수정 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		Board updateArticle = boardService.update(postId, request);

		return ResponseEntity.ok()
				.body(updateArticle);
	}
}
