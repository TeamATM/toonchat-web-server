package com.webtoonchat.toonchat.dto.chat;

import java.time.LocalDateTime;

import com.webtoonchat.toonchat.domain.chat.Chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AddChatRequest {
	private String id;
	private String message;
	private String sender;
	private String receiver;
	private LocalDateTime createdAt;
	public Chat toEntity() {
		return Chat.builder()
			.id(id)
			.message(message)
			.sender(sender)
			.receiver(receiver)
			.build();
	}
}
