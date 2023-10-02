package com.webtoonchat.toonchat.controller.board;

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

import com.webtoonchat.toonchat.domain.board.Board;
import com.webtoonchat.toonchat.dto.board.AddBoardRequest;
import com.webtoonchat.toonchat.dto.board.BoardResponse;
import com.webtoonchat.toonchat.dto.board.UpdateBoardRequest;
import com.webtoonchat.toonchat.service.board.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
	private final BoardService boardService;

	@PostMapping("/api/boards")
	public ResponseEntity<Board> addBoard(@RequestBody AddBoardRequest request) {
		Board savedArticle = boardService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedArticle);
	}

	@GetMapping("/api/boards")
	public ResponseEntity<List<BoardResponse>> findAllBoards() {
		List<BoardResponse> articles = boardService.findAll()
				.stream()
				.map(BoardResponse::new) // .map(x -> new ArticleResponse(x))
				.toList();

		return ResponseEntity.ok()
				.body(articles);
	}

	@GetMapping("/api/boards/{id}")
	public ResponseEntity<BoardResponse> findBoard(@PathVariable long id) {
		Board article = boardService.findById(id);

		return ResponseEntity.ok()
				.body(new BoardResponse(article));
	}

	@DeleteMapping("/api/boards/{id}")
	public ResponseEntity<Void> deleteBoard(@PathVariable long id) {
		boardService.delete(id);
		return ResponseEntity.ok()
				.build();
	}

	@PutMapping("/api/boards/{id}")
	public ResponseEntity<Board> updateBoard(
			@PathVariable long id,
			@RequestBody UpdateBoardRequest request
	) {
		Board updateArticle = boardService.update(id, request);

		return ResponseEntity.ok()
				.body(updateArticle);
	}
}
