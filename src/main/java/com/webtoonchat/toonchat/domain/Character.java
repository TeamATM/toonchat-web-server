package com.webtoonchat.toonchat.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Column(name = "character_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long characterId;

	@Column
	private String profileUrl;

	@Column
	private String backgroundUrl;

	@Column
	private String stateMessage;

	@Column
	private String hashtags;

	@Column
	private String name;

	public Character(String name, String profileUrl, String backgroundUrl, String stateMessage, String hashtags) {
		this.name = name;
		this.profileUrl = profileUrl;
		this.backgroundUrl = backgroundUrl;
		this.stateMessage = stateMessage;
		this.hashtags = hashtags;
	}
}
