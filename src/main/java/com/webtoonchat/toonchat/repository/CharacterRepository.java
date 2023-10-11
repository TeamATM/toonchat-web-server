package com.webtoonchat.toonchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
