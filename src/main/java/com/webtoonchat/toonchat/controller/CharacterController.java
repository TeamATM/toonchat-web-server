package com.webtoonchat.toonchat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.webtoonchat.toonchat.domain.Characters;
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
	public ResponseEntity<Characters> createCharacter(@RequestBody Characters characters) {
		return ResponseEntity.ok(characterService.createCharacter(characters));
	}

	@Operation(description = "모든 캐릭터 프로필 조회")
	@GetMapping
	public ResponseEntity<List<Characters>> getAllCharacters() {
		return ResponseEntity.ok(characterService.getAllCharacters());
	}

	@Operation(description = "code로 캐릭터 조회")
	@GetMapping("/{code}")
	public ResponseEntity<Characters> getCharacterByCode(@PathVariable String code) {
		return ResponseEntity.ok(characterService.getCharacterByCode(code));

	}
}

