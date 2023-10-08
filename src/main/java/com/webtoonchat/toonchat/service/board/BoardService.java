package com.webtoonchat.toonchat.service.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.board.Board;
import com.webtoonchat.toonchat.dto.board.AddBoardRequest;
import com.webtoonchat.toonchat.dto.board.UpdateBoardRequest;
import com.webtoonchat.toonchat.repository.BoardRepository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BoardService {
	private final BoardRepository boardRepository;

	public Board save(AddBoardRequest request, String bgno) {
		request.setBgno(bgno);
		return boardRepository.save(request.toEntity());
	}

	public List<Board> findAll() {
		return boardRepository.findAll();
	}


	public List<Board> findAllByBgno(String bgno) {
		return boardRepository.findAllByBgno(bgno);
	}


	public Board findById(long id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found: " + id));
	}

	public void delete(long id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public Board update(long id, UpdateBoardRequest request) {
		Board article = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found : " + id));
		article.update(request.getTitle(), request.getContent());

		return article;
	}
}
