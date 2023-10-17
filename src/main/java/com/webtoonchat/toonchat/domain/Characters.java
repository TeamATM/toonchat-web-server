package com.webtoonchat.toonchat.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "characters")
@NoArgsConstructor
@Entity
public class Characters {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String code;

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

	@JsonManagedReference
	@OneToMany(mappedBy = "characters") // 수정된 부분
	private Set<Friendship> friendships = new HashSet<>(); // 수정된 부분
}
