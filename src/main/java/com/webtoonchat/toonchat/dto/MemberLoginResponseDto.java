package com.webtoonchat.toonchat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDto {
	private String accessToken;
	private String refreshToken;
	private Long memberId;
	private String nickname;
	private String profileUrl;
	private String provider;
}