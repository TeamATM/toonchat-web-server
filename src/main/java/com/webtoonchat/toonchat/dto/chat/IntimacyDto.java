package com.webtoonchat.toonchat.dto.chat;

import com.webtoonchat.toonchat.domain.chat.Intimacy;

import lombok.Data;


@Data
public class IntimacyDto {
	private Long id;
	private String userName;
	private Long score;
	private Long level;
	private Long gauge;

	public Intimacy toEntity() {
		Intimacy entity = new Intimacy();
		entity.setUserName(userName);
		entity.setScore(score);
		entity.setLevel(score / 100);
		entity.setGauge(score % 100);

		return entity;
	}
}
