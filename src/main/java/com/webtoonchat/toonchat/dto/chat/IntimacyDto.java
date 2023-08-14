package com.webtoonchat.toonchat.dto.chat;

import com.webtoonchat.toonchat.domain.chat.Intimacy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IntimacyDto {
	private Long id;
	private String userName;
	private String characterName;
	private Long score;

	public IntimacyDto setId(Long id) {
		this.id = id;
		return this;
	}

	public IntimacyDto setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public IntimacyDto setCharacterName(String characterName) {
		this.characterName = characterName;
		return this;
	}

	public IntimacyDto setScore(Long score) {
		this.score = score;
		return this;
	}

	public Intimacy toEntity() {
		Intimacy entity = new Intimacy();
		entity.setId(this.id);
		entity.setUserName(this.userName);
		entity.setCharacterName(this.characterName);
		entity.setLevel(this.score / 100);
		entity.setGauge(this.score % 100);

		return entity;
	}
}
