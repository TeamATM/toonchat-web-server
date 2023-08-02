package com.webtoonchat.toonchat.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.Character;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
	Optional<Character> findById(String id);

}
