package com.webtoonchat.toonchat.service.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.chat.Chat;
import com.webtoonchat.toonchat.dto.chat.AddChatRequest;
import com.webtoonchat.toonchat.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {
	private final ChatRepository chatRepository;

	public List<Chat> getUserChatHistory(String username) {
		return chatRepository.findBySenderOrReceiver(username, username);
	}

	public Chat save(AddChatRequest request) {
		return chatRepository.save(request.toEntity());
	}
}
