package com.webtoonchat.toonchat.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;

@Repository
public interface StompMessageRepository extends MongoRepository<StompMessageEntity, String>  {
	//List<StompMessageEntity> findByMessageFromOrMessageTo(String messageFrom, String messageTo);

	@Query(sort = "{_id:-1}")
	List<StompMessageEntity> findByUserIdAndCharacterName(String userId, String characterName);

	List<StompMessageEntity> findByMessageId(String messageId);
}

