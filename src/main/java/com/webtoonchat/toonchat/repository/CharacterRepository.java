package com.webtoonchat.toonchat.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.Character;

//@Repository
//public interface CharacterRepository extends MongoRepository<Character, String> {
//	Optional<Character> findByCid(String id);
//
//}
public interface CharacterRepository extends JpaRepository<Character, Long> {

	// 웹툰 ID로 캐릭터 조회 메서드
	Optional<Character> findByCid(String id);
}
