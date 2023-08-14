package com.webtoonchat.toonchat.service.chat;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.chat.Intimacy;
import com.webtoonchat.toonchat.repository.IntimacyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IntimacyService {
	private final IntimacyRepository intimacyRepository;

	public Intimacy save(Intimacy intimacy) {
		return intimacyRepository.save(intimacy);
	}

	public Optional<Intimacy> findByUserName(String username) {
		return intimacyRepository.findByUserName(username);
	}

}

