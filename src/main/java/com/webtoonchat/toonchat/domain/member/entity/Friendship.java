package com.webtoonchat.toonchat.domain.member.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.data.annotation.CreatedDate;

import com.webtoonchat.toonchat.domain.character.entity.Character;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "friendship")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Friendship {
	@EmbeddedId
	FriendshipId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("memberId")
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("characterId")
	@JoinColumn(name = "character_id")
	private Character character;

	@CreatedDate
	private LocalDateTime createdAt;

	public Friendship(FriendshipId friendshipId, Member member, Character character) {
		this.id = friendshipId;
		this.member = member;
		this.character = character;
		this.createdAt = LocalDateTime.now(ZoneOffset.ofHours(9));
	}
}
