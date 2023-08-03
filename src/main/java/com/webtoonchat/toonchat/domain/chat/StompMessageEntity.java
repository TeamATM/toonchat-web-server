package com.webtoonchat.toonchat.domain.chat;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "stomp_messages")
public class StompMessageEntity {
	@Id
	private String id;
	private String messageId;
	private String replyMessageId;
	private String status;
	private String content;
	private String messageFrom;
	private String messageTo;
	private String characterName;
	private Long createdAt;
	private Boolean doStream = Boolean.FALSE;
}
