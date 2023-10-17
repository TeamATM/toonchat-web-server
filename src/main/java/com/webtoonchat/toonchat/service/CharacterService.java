package com.webtoonchat.toonchat.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.Characters;
import com.webtoonchat.toonchat.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;

	public Characters createCharacter(Characters characters) {
		return characterRepository.save(characters);
	}

	public List<Characters> getAllCharacters() {
		return characterRepository.findAll();
	}

	public Characters getCharacterByCode(String code) {
		return characterRepository.findByCode(code);
	}

}
