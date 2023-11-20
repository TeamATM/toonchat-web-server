package com.webtoonchat.toonchat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLogoutRequestDto {
	private String accessToken;
	private String refreshToken;

	public String getToken() {
		if (accessToken == null && refreshToken == null) {
			throw new IllegalArgumentException("토큰값이 존재하지 않습니다.");
		}
		return accessToken != null ? accessToken : refreshToken;
	}
}
