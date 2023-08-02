package com.webtoonchat.toonchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByNickname(String nickname);
}
