package com.webtoonchat.toonchat.domain.character.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webtoonchat.toonchat.domain.character.dto.CharacterRegisterDto;
import com.webtoonchat.toonchat.domain.character.dto.CharacterResponseDto;
import com.webtoonchat.toonchat.domain.character.service.CharacterService;
import com.webtoonchat.toonchat.resolver.annotation.Login;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {

	private final CharacterService characterService;

	@Operation(description = "새로운 캐릭터 프로필 생성")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<CharacterResponseDto> createCharacter(
		@RequestPart(value = "characterInfo") @Validated CharacterRegisterDto characterRegisterDto,
		@RequestPart MultipartFile profileImage,
		@RequestPart MultipartFile backgroundImage,
		@Login Claims claims) {

		log.info("profileImageSize={}", profileImage.getSize());
		log.info("backgroundImageSize={}", backgroundImage.getSize());

		CharacterResponseDto characterResponseDto = characterService.createCharacter(characterRegisterDto,
			claims.get("userId", Long.class));
		return ResponseEntity.ok(characterResponseDto);
	}

	@Operation(description = "모든 캐릭터 프로필 조회")
	@GetMapping
	public ResponseEntity<List<CharacterResponseDto>> getAllCharacters() {
		return ResponseEntity.ok(characterService.getAllCharacters());
	}

	@Operation(description = "캐릭터 조회")
	@GetMapping("/{characterId}")
	public ResponseEntity<CharacterResponseDto> getCharacter(@PathVariable Long characterId) {
		CharacterResponseDto characterResponseDto = characterService.getCharacterDtoById(characterId);
		return ResponseEntity.ok(characterResponseDto);
	}
}

