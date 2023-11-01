package com.webtoonchat.toonchat.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
			message = "이메일 형식을 맞춰야합니다")
	private String email;

	private String password;

	@NotEmpty
	@Size(min = 2, max = 15, message = "이름은 2글자부터 15글자까지 가능합니다.")
	private String name;

	private String provider;
}
