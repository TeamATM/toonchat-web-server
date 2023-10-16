package com.webtoonchat.toonchat.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Table(name = "characters")
@NoArgsConstructor
@Entity
public class Characters {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "character_id")
	private String characterId;

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

	@OneToMany(mappedBy = "character") // 수정된 부분
	private List<Friendship> friendships; // 수정된 부분
}
