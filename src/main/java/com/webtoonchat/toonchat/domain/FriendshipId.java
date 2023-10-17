package com.webtoonchat.toonchat.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class FriendshipId implements Serializable {

	private Long member;
	private Long characters;
}
