package com.webtoonchat.toonchat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Table(name = "\"character\"")
@NoArgsConstructor
@Entity
public class Character {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("characterId")
	private Long id;

	@Column
	private String profileImageUrl;

	@Column
	private String backgroundImageUrl;

	@Column
	private String statusMessage;

	@Column
	private String hashTag;

	@Column
	private String characterName;

	public Character(String characterName,
		String profileImageUrl,
		String backgroundImageUrl,
		String statusMessage,
		String hashTag) {
		this.characterName = characterName;
		this.profileImageUrl = profileImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.statusMessage = statusMessage;
		this.hashTag = hashTag;
	}
}
