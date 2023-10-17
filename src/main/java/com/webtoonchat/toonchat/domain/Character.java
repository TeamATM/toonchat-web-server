package com.webtoonchat.toonchat.domain;

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
	private Long id;

	@Column
	private String profileImageUrl;

	@Column
	private String backgroundImageUrl;

	@Column
	private String stateMessage;

	@Column
	private String hashTag;

	@Column
	private String name;

	public Character(String name,
		String profileImageUrl,
		String backgroundImageUrl,
		String stateMessage,
		String hashTag) {
		this.name = name;
		this.profileImageUrl = profileImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.stateMessage = stateMessage;
		this.hashTag = hashTag;
	}
}
