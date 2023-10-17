package com.webtoonchat.toonchat.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.domain.board.Board;
import com.webtoonchat.toonchat.dto.board.AddBoardRequest;
import com.webtoonchat.toonchat.dto.board.BoardResponse;
import com.webtoonchat.toonchat.dto.board.UpdateBoardRequest;
import com.webtoonchat.toonchat.resolver.annotation.Login;
import com.webtoonchat.toonchat.security.jwt.util.JwtTokenizer;
import com.webtoonchat.toonchat.service.MemberService;
import com.webtoonchat.toonchat.service.board.BoardService;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	private final MemberService memberService;
	@Operation(description = "특정 캐릭터 게시판 게시글 작성")
	@PostMapping("/api/boards/{characterId}")
	public ResponseEntity<Board> addBoard(@PathVariable Long characterId,
		@RequestBody AddBoardRequest request,
		@Login Claims claims) {
		Long userid = ((Integer) claims.get("userId")).longValue();
		Member member = memberService.findByMemberId(userid);
		Board savedArticle = boardService.save(request, characterId, member.getName(), userid);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}

	@Operation(description = "특정 캐릭터 게시판 모든 게시글 조회")
	@GetMapping("/api/boards/{characterId}")
	public ResponseEntity<List<BoardResponse>> findAllBoards(@PathVariable Long characterId) {
		List<BoardResponse> articles = boardService.findAllByCharacterId(characterId)
				.stream()
				.map(BoardResponse::new)
				.toList();

		return ResponseEntity.ok()
				.body(articles);
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 조회")
	@GetMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<BoardResponse> findBoard(@PathVariable long id) {
		Board article = boardService.findById(id);

		return ResponseEntity.ok()
				.body(new BoardResponse(article));
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 삭제")
	@DeleteMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<Void> deleteBoard(@PathVariable long id, @Login Claims claims) {
		Long userid = ((Integer) claims.get("userId")).longValue();
		if (!boardService.findById(id).getWriterId().equals(userid)) {
			log.error("작성자가 아닌 글 삭제 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		boardService.delete(id);
		return ResponseEntity.ok()
				.build();
	}

	@Operation(description = "특정 캐릭터 게시판 특정 게시글 수정")
	@PutMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<Board> updateBoard(
		@PathVariable long id,
		@RequestBody UpdateBoardRequest request,
		@Login Claims claims
	) {
		Long userid = ((Integer) claims.get("userId")).longValue();
		if (!boardService.findById(id).getWriterId().equals(userid)) {
			log.error("작성자가 아닌 글 수정 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		Board updateArticle = boardService.update(id, request);

		return ResponseEntity.ok()
				.body(updateArticle);
	}
}
