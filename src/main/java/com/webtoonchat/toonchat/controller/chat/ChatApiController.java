package com.webtoonchat.toonchat.controller.chat;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.SessionUtils;
import com.webtoonchat.toonchat.domain.chat.Character;
import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;
import com.webtoonchat.toonchat.service.chat.CharacterService;
import com.webtoonchat.toonchat.service.chat.StompMessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatApiController {
	private final StompMessageService stompMessageService;
	private final CharacterService characterService;

	@GetMapping("/api/chat/{id}")
	public ResponseEntity<StompMessageEntity> getLastChat(
		@PathVariable String id, @RequestParam(required = false, defaultValue = "false"
	) boolean recent) {
		if (recent) {
			String username = SessionUtils.getUserName();
			username = "anonymous-05c4e7a7-e21b-474a-8092-d89918901dd6";
			/**
			 * TO DO : anonymousUser가 나와서 못찾는 문제 해결
			 * 현재는 anonymous를 넣는 방식으로 땜빵했지만, 이후에 로그인 기능 생기면 보안 정책을 맞춰서 해결해야함
			 */
			Optional<Character> character = characterService.getCharacterInfo(id);
			String characterName = character.map(Character::getBotName).orElse("There is No bot to talk");

			StompMessageEntity lastChat = stompMessageService.getLastChat(username, characterName);
			return ResponseEntity.status(HttpStatus.OK)
				.body(lastChat);
		}
		return null;
	}

	@GetMapping("/api/chat/history/{id}")
	public ResponseEntity<List<StompMessageEntity>> getChatHistory(@PathVariable String id) {
		String username = SessionUtils.getUserName();
		username = "anonymous-05c4e7a7-e21b-474a-8092-d89918901dd6";

		Optional<Character> character = characterService.getCharacterInfo(id);
		String characterName = character.map(Character::getBotName).orElse("There is No bot to talk");

		List<StompMessageEntity> chatHistory = stompMessageService.getUserChatHistory(username, characterName);

		return ResponseEntity.status(HttpStatus.OK)
			.body(chatHistory);
	}

	@GetMapping("/api/info/character/{id}")
	public ResponseEntity<Character> getCharacterInfo(@PathVariable String id) {
		Optional<Character> character = characterService.getCharacterInfo(id);
		if (character.isEmpty()) {
			ResponseEntity.notFound();
		}
		return ResponseEntity.status(HttpStatus.OK)
			.body(character.get());
	}
}
