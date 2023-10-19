package com.webtoonchat.toonchat.dto.message;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webtoonchat.toonchat.domain.Character;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterUpdateMessageDto {
	CharacterMessageOp op;
	@JsonProperty("_id")
	Long id;
	String characterName;
	String profileImageUrl;
	String backgroundImageUrl;
	String statusMessage;
	String hashTag;
	String persona;

	public CharacterUpdateMessageDto(CharacterMessageOp op, Character character) {
		this.op = op;
		this.id = character.getId();
		this.characterName = character.getCharacterName();
		this.profileImageUrl = character.getProfileImageUrl();
		this.backgroundImageUrl = character.getBackgroundImageUrl();
		this.statusMessage = character.getStatusMessage();
		this.hashTag = character.getHashTag();
		this.persona = character.getPersona();
	}

	public CharacterUpdateMessageDto(CharacterMessageOp op, Long id) {
		this.op = op;
		this.id = id;
	}
}
