package com.webtoonchat.toonchat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Characters;


public interface CharacterRepository extends JpaRepository<Characters, Long> {
	List<Characters> findAll();

	Optional<Characters> findByCharacterId(String characterId);


}
