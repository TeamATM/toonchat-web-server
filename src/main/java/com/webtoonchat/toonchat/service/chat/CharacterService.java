package com.webtoonchat.toonchat.service.chat;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.chat.Character;
import com.webtoonchat.toonchat.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CharacterService {
	/**
	 * 캐릭터 정보 가져오기 서비스
	 */
	private final CharacterRepository characterRepository;

	public Optional<Character> getCharacterInfo(String id) {
		return characterRepository.findByCid(id);
	}
}
