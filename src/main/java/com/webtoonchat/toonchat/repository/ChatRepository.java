package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.Chat;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
	List<Chat> findBySenderOrReceiver(String sender, String receiver);

	List<Chat> findAllByOrderByCreatedAtDesc();
}
