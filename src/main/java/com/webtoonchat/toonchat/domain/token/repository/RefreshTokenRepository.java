package com.webtoonchat.toonchat.domain.token.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.token.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByValue(String value);
}
