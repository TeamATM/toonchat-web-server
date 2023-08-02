package com.webtoonchat.toonchat.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddUserRequest {
	private String email;
	private String nickname;
	private String password;
}
