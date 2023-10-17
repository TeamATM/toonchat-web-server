package com.webtoonchat.toonchat.controller.dto;

import com.webtoonchat.toonchat.domain.Character;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CharacterRegisterDto {
	@NotBlank
	private String name;
	@NotBlank
	private String stateMessage;
	private String hashtags;
	private String profileUrl;
	private String backgroundUrl;

	public Character toEntity() {
		return new Character(name, profileUrl, backgroundUrl, stateMessage, hashtags);
	}
}
