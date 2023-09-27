package com.webtoonchat.toonchat.dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class MemberSignupResponseDto {
	private Long memberId;
	private String email;
	private String name;
	private LocalDateTime regdate;
	/**
	 * TO-Do : 프로필 url, provider column 만들기
	 */
}
