package com.webtoonchat.toonchat.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.controller.dto.CharacterRegisterDto;
import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {

	private final CharacterService characterService;

	@Operation(description = "새로운 캐릭터 프로필 생성")
	@PostMapping
	public ResponseEntity<Character> createCharacter(
		@RequestBody @Validated CharacterRegisterDto characterRegisterDto) {
		return ResponseEntity.ok(characterService.createCharacter(characterRegisterDto.toEntity()));
	}

	@Operation(description = "모든 캐릭터 프로필 조회")
	@GetMapping
	public ResponseEntity<List<Character>> getAllCharacters() {
		return ResponseEntity.ok(characterService.getAllCharacters());
	}

	@Operation(description = "캐릭터 조회")
	@GetMapping("/{characterId}")
	public ResponseEntity<Character> getCharacter(@PathVariable Long characterId) throws NoSuchElementException {
		return ResponseEntity.ok(characterService.getCharacterById(characterId));
	}
}

