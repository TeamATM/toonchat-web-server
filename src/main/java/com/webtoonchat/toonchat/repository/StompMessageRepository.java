package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;

@Repository
public interface StompMessageRepository extends MongoRepository<StompMessageEntity, String>  {
	List<StompMessageEntity> findByMessageFromOrMessageTo(String messageFrom, String messageTo);

	List<StompMessageEntity> findByMessageId(String messageId);
}

