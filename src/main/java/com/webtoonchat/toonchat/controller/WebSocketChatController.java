package com.webtoonchat.toonchat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.webtoonchat.toonchat.SessionUtils;
import com.webtoonchat.toonchat.domain.chat.Character;
import com.webtoonchat.toonchat.domain.chat.Chat;
import com.webtoonchat.toonchat.dto.StompMessageDto;
import com.webtoonchat.toonchat.service.chat.CharacterService;
import com.webtoonchat.toonchat.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {
	private final SimpMessageSendingOperations messageTemplate;
	private final CharacterService characterService;
	private final ChatService chatService;
	@MessageMapping("/{id}")
	public void sendMessage(@Payload StompMessageDto stompMessageDto, @DestinationVariable String id) {
		String username = SessionUtils.getUserName();
		// TODO: id 통해서 캐릭터 이름 가져오기
		Optional<Character> character = characterService.getCharacterInfo(id);
		String characterName = character.map(Character::getBotName).orElse("There is No bot to talk");
		//String characterName = id.equals("0") ? "이영준" : "김미소";

		stompMessageDto.setReplyMessageId()
			.setMessageFrom(username)
			.setMessageTo(id)
			.setStatus("STARTED")
			.setCharacterName(characterName);
		// TODO: history 가져오기(자료형 바꿔도 OK)
		//chatService.save(stompMessageDto);
		List<Chat> history = chatService.getUserChatHistory(username);

		messageTemplate.convertAndSend("/exchange/celery/celery",
			stompMessageDto.toCeleryMessageDto("inference", history));
		messageTemplate.convertAndSend("/topic/" + username, stompMessageDto);
	}
}
