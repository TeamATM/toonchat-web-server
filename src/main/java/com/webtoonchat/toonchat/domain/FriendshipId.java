package com.webtoonchat.toonchat.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendshipId implements Serializable {
	@Column(name = "member_id")
	private Long memberId;
	@Column(name = "character_id")
	private Long characterId;
}
