package com.webtoonchat.toonchat.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webtoonchat.toonchat.domain.Character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CharacterResponseDto {
	@JsonProperty("characterId")
	Long id;
	String characterName;
	String profileImageUrl;
	String backgroundImageUrl;
	String statusMessage;
	String hashTag;

	public CharacterResponseDto(Character character) {
		this.id = character.getId();
		this.characterName = character.getCharacterName();
		this.profileImageUrl = character.getProfileImageUrl();
		this.backgroundImageUrl = character.getBackgroundImageUrl();
		this.statusMessage = character.getStatusMessage();
		this.hashTag = character.getHashTag();
	}

	public CharacterResponseDto(Long id, String characterName, String profileImageUrl, String backgroundImageUrl,
		String statusMessage, String hashTag) {
		this.id = id;
		this.characterName = characterName;
		this.profileImageUrl = profileImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.statusMessage = statusMessage;
		this.hashTag = hashTag;
	}
}
