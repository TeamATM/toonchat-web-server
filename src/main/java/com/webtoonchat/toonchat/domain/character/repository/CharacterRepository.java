package com.webtoonchat.toonchat.domain.character.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webtoonchat.toonchat.domain.character.dto.CharacterResponseDto;
import com.webtoonchat.toonchat.domain.character.entity.Character;


public interface CharacterRepository extends JpaRepository<Character, Long> {
	@Query("select new com.webtoonchat.toonchat.domain.character.dto.CharacterResponseDto("
		+ "c.id, c.characterName, c.profileImageUrl, c.backgroundImageUrl, c.statusMessage, c.hashTag) "
		+ "from Character c")
	List<CharacterResponseDto> findAllCharacter();
}
