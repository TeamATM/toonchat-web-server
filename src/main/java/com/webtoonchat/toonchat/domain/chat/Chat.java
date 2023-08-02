package com.webtoonchat.toonchat.domain.chat;

// import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Document(collection = "chat")
public class Chat {
	@Id
	private String id;
	private String message;
	private String sender;
	private String receiver;

	@CreatedDate
	private LocalDateTime createdAt;
	@Builder // 빌더 패턴으로 객체 생성
	public Chat(String id, String message, String sender, String receiver, LocalDateTime createdAt) {
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.createdAt = LocalDateTime.now();
	}
}
