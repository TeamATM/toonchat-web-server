package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Character;


public interface CharacterRepository extends JpaRepository<Character, Long> {
	List<Character> findAll();
}
