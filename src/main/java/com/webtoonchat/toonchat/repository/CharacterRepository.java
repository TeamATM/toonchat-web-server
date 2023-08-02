package com.webtoonchat.toonchat.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.Character;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
}
