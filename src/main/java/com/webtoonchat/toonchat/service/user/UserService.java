package com.webtoonchat.toonchat.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.user.User;
import com.webtoonchat.toonchat.dto.user.AddUserRequest;
import com.webtoonchat.toonchat.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	//private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Long save(AddUserRequest dto) {
		return userRepository.save(User.builder()
			.email(dto.getEmail())
			.nickname(dto.getNickname())
			//.password(bCryptPasswordEncoder.encode(dto.getPassword()))
			.build()).getId();
	}
}
