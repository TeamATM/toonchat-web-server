package com.webtoonchat.toonchat.service;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.repository.CharacterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
	private final CharacterRepository characterRepository;

}
