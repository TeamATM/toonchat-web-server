package com.webtoonchat.toonchat.domain.character.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.character.dto.CharacterRegisterDto;
import com.webtoonchat.toonchat.domain.character.dto.CharacterResponseDto;
import com.webtoonchat.toonchat.domain.character.entity.Character;
import com.webtoonchat.toonchat.domain.character.repository.CharacterRepository;
import com.webtoonchat.toonchat.domain.member.entity.Member;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.domain.message.dto.CharacterMessageOp;
import com.webtoonchat.toonchat.domain.message.dto.CharacterUpdateMessageDto;
import com.webtoonchat.toonchat.domain.message.service.MessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;
	private final MemberService memberService;
	private final MessageService messageService;

	@Transactional(readOnly = false)
	public CharacterResponseDto createCharacter(CharacterRegisterDto characterRegisterDto, Long memberId) {
		Member memberReference = memberService.getMemberReference(memberId);
		Character character = new Character(characterRegisterDto, "/", "/", memberReference);
		Character savedCharacter = characterRepository.save(character);

		CharacterUpdateMessageDto updateMessageDto =
			new CharacterUpdateMessageDto(CharacterMessageOp.CREATE, savedCharacter);
		messageService.sendCharacterUpdateMessage(updateMessageDto);

		return new CharacterResponseDto(character);
	}

	@Transactional
	public void deleteCharacter(Long characterId) {
		characterRepository.deleteById(characterId);

		CharacterUpdateMessageDto updateMessageDto =
			new CharacterUpdateMessageDto(CharacterMessageOp.DELETE, characterId);
		messageService.sendCharacterUpdateMessage(updateMessageDto);
	}

	public List<CharacterResponseDto> getAllCharacters() {
		return characterRepository.findAllCharacter();
	}

	public Character getCharacterById(Long characterId) throws NoSuchElementException {
		Character character = characterRepository.findById(characterId)
			.orElseThrow(() -> new NoSuchElementException("캐릭터가 존재하지 않습니다."));

		return character;
	}

	public CharacterResponseDto getCharacterDtoById(Long characterId) throws NoSuchElementException {
		return new CharacterResponseDto(getCharacterById(characterId));
	}

	public boolean isCharacterExist(Long characterId) {
		if (characterId == null || characterId < 0) {
			return false;
		}

		return characterRepository.existsById(characterId);
	}
}
