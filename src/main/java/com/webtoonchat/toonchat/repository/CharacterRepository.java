package com.webtoonchat.toonchat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Character;


public interface CharacterRepository extends JpaRepository<Character, Long> {
	public List<Character> findAll();

	public Optional<Character> findByCharacterId(String characterId);


}
