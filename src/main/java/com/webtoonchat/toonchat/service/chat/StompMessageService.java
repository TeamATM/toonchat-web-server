package com.webtoonchat.toonchat.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.chat.Chat;
import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;
import com.webtoonchat.toonchat.dto.StompMessageDto;
import com.webtoonchat.toonchat.dto.chat.AddChatRequest;
import com.webtoonchat.toonchat.repository.StompMessageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StompMessageService {
	private final StompMessageRepository stompMessageRepository;

	public List<StompMessageEntity> getUserChatHistory(String username) {
		return stompMessageRepository.findByMessageFromOrMessageTo(username, username);
	}

	public boolean messageExists(String messageId) {
		List<StompMessageEntity> messages = stompMessageRepository.findByMessageId(messageId);
		return messages.size() == 1;
	}

	public StompMessageEntity save(StompMessageDto request) {
		return stompMessageRepository.save(request.toEntity());
	}
}
