package com.webtoonchat.toonchat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "characters")
@NoArgsConstructor
@Entity
public class Character {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String characterId;

	@Column
	private String profileUrl;

	@Column
	private String backgroundUrl;

	@Column
	private String stateMessage;

	@Column
	private String name;
}
