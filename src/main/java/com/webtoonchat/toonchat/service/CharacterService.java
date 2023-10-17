package com.webtoonchat.toonchat.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;

	public Character createCharacter(Character character) {
		return characterRepository.save(character);
	}

	public List<Character> getAllCharacters() {
		return characterRepository.findAll();
	}

	public Character getCharacterById(Long characterId) throws NoSuchElementException {
		return characterRepository.findById(characterId)
			.orElseThrow(() -> new NoSuchElementException("캐릭터가 존재하지 않습니다."));
	}

	public boolean isCharacterExist(Long characterId) {
		if (characterId == null || characterId < 0) {
			return false;
		}

		return characterRepository.existsById(characterId);
	}
}
