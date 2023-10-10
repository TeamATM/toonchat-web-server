package com.webtoonchat.toonchat.controller.board;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.board.Board;
import com.webtoonchat.toonchat.dto.board.AddBoardRequest;
import com.webtoonchat.toonchat.dto.board.BoardResponse;
import com.webtoonchat.toonchat.dto.board.UpdateBoardRequest;
import com.webtoonchat.toonchat.security.jwt.util.JwtTokenizer;
import com.webtoonchat.toonchat.service.board.BoardService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;
	private final JwtTokenizer jwtTokenizer;

	@Value("${jwt.secretKey}")
	private String secretKey;

	@PostMapping("/api/boards/{characterId}")
	public ResponseEntity<Board> addBoard(
			@PathVariable String characterId,
			@RequestBody AddBoardRequest request, HttpServletRequest httpServletRequest) {
		Claims claims = extracted(httpServletRequest);
		Integer userid = (Integer) claims.get("userId");
		Board savedArticle = boardService.save(request, characterId, userid);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}

	@GetMapping("/api/boards/{characterId}")
	public ResponseEntity<List<BoardResponse>> findAllBoards(
			@PathVariable String characterId, HttpServletRequest httpServletRequest) {
		List<BoardResponse> articles = boardService.findAllByBgno(characterId)
				.stream()
				.map(BoardResponse::new)
				.toList();

		return ResponseEntity.ok()
				.body(articles);
	}

	@GetMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<BoardResponse> findBoard(
			@PathVariable String characterId, @PathVariable long id, HttpServletRequest httpServletRequest) {
		Board article = boardService.findById(id);

		return ResponseEntity.ok()
				.body(new BoardResponse(article));
	}

	@DeleteMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<Void> deleteBoard(
			@PathVariable String characterId, @PathVariable long id, HttpServletRequest httpServletRequest) {
		Claims claims = extracted(httpServletRequest);
		if (boardService.findById(id).getWriterId() != (int)claims.get("userId")) {
			log.error("작성자가 아닌 글 삭제 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		boardService.delete(id);
		return ResponseEntity.ok()
				.build();
	}

	@PutMapping("/api/boards/{characterId}/{id}")
	public ResponseEntity<Board> updateBoard(
			@PathVariable String characterId,
			@PathVariable long id,
			@RequestBody UpdateBoardRequest request,
			HttpServletRequest httpServletRequest
	) {
		Claims claims = extracted(httpServletRequest);
		if (boardService.findById(id).getWriterId() != (int)claims.get("userId")) {
			log.error("작성자가 아닌 글 수정 요청");
			return ResponseEntity.badRequest()
					.build();
		}
		Board updateArticle = boardService.update(id, request);

		return ResponseEntity.ok()
				.body(updateArticle);
	}

	private Claims extracted(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");
		String[] arr = authorization.split(" ");
		log.info(arr[1]);
		return jwtTokenizer.parseToken(arr[1], secretKey.getBytes(StandardCharsets.UTF_8));
	}
}
