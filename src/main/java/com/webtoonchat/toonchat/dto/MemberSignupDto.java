package com.webtoonchat.toonchat.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupDto {

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
			message = "이메일 형식을 맞춰야합니다")
	private String email;

	private String password;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z가-힣\\\\s]{2,15}",
			message = "이름은 영문자, 한글, 공백포함 2글자부터 15글자까지 가능합니다.")
	private String name;

	private String provider;
}
