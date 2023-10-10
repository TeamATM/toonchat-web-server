package com.webtoonchat.toonchat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Character {
	@Id
	@Column(name = "character_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long characterId;

	@Column
	private String profileUrl;

	@Column
	private String backgroundUrl;

	@Column(length = 255)
	private String stateMessage;

	@Column(length = 50)
	private String name;
}
