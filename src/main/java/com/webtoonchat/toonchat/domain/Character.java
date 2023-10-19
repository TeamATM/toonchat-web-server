package com.webtoonchat.toonchat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webtoonchat.toonchat.controller.dto.CharacterRegisterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@Column
	private String persona;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member owner;

	public Character(CharacterRegisterDto registerDto, String profileImageUrl,
		String backgroundImageUrl, Member owner) {
		this.characterName = registerDto.getName();
		this.statusMessage = registerDto.getStatusMessage();
		this.hashTag = registerDto.getHashTag();
		this.persona = registerDto.getPersona();
		this.profileImageUrl = profileImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.owner = owner;
	}
}
