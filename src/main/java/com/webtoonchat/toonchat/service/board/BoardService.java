package com.webtoonchat.toonchat.service.board;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.board.Board;
import com.webtoonchat.toonchat.dto.board.AddBoardRequest;
import com.webtoonchat.toonchat.dto.board.UpdateBoardRequest;
import com.webtoonchat.toonchat.repository.BoardRepository;
import com.webtoonchat.toonchat.repository.MemberRepository;
import com.webtoonchat.toonchat.service.CharacterService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final CharacterService characterService;

	public Board save(AddBoardRequest request, Long characterId, String userName, Long userId) {
		if (!characterService.isCharacterExist(characterId)) {
			throw new NoSuchElementException("캐릭터가 존재하지 않습니다.");
		}
		request.setCharacterId(characterId);
		request.setWriter(userName);
		request.setWriterId(userId);
		return boardRepository.save(request.toEntity());
	}

	public List<Board> findAll() {
		return boardRepository.findAll();
	}


	public List<Board> findAllByCharacterId(Long characterId) {
		return boardRepository.findAllByCharacterId(characterId);
	}


	public Board findById(long id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
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

