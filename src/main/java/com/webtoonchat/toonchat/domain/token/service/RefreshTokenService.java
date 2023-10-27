package com.webtoonchat.toonchat.domain.token.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.token.entity.RefreshToken;
import com.webtoonchat.toonchat.domain.token.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	@Transactional
	public RefreshToken addRefreshToken(RefreshToken refreshToken) {
		return refreshTokenRepository.save(refreshToken);
	}

//	@Transactional
//	public void deleteRefreshToken(String refreshToken) {
//		refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenRepository::delete);
//	}

	@Transactional
	public void deleteRefreshToken(Long userId) {
		refreshTokenRepository.deleteByMemberId(userId);
	}

	@Transactional(readOnly = true)
	public Optional<RefreshToken> findRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByValue(refreshToken);
	}
}
