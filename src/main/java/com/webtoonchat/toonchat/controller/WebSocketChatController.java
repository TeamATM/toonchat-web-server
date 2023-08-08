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
import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;
import com.webtoonchat.toonchat.dto.StompMessageDto;
import com.webtoonchat.toonchat.service.chat.CharacterService;
import com.webtoonchat.toonchat.service.chat.StompMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {
	private final SimpMessageSendingOperations messageTemplate;
	private final CharacterService characterService;
	private final StompMessageService stompMessageService;

	@MessageMapping("/{id}")
	public void sendMessage(@Payload StompMessageDto stompMessageDto, @DestinationVariable String id) {
		String username = SessionUtils.getUserName();
		System.out.println("username = " + username);
		//캐릭터 이름 가져오기
		Optional<Character> character = characterService.getCharacterInfo(id);
		String characterName = character.map(Character::getBotName).orElse("There is No bot to talk");

		stompMessageDto.setUserId(username)
			.setReplyMessageId()
			.setMessageFrom(username)
			.setMessageTo(id)
			.setStatus("STARTED")
			.setCharacterName(characterName);
		// history 가져오기
		List<StompMessageEntity> history = stompMessageService.getUserChatHistory(username, characterName);
		//		System.out.println("-------hiihi---------------");
		//
		//		for (StompMessageEntity message : history) {
		//			// 여기에서 StompMessageEntity의 필드를 사용하여 원하는 출력 방식으로 조합합니다.
		//			// 예를 들면, message.getSender(), message.getContent() 등을 이용해 출력할 수 있습니다.
		//			System.out.println("message = " + message.getUserId());
		//			System.out.println("message.getCharacterName() = " + message.getCharacterName());
		//			System.out.println("-------------------------------");
		//		}
		// 채팅 저장
		stompMessageService.save(stompMessageDto);
		messageTemplate.convertAndSend("/exchange/celery/celery",
			stompMessageDto.toCeleryMessageDto("inference", history));
		messageTemplate.convertAndSend("/topic/" + username, stompMessageDto);
	}
}
