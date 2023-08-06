package com.webtoonchat.toonchat.controller.chat;

import com.webtoonchat.toonchat.SessionUtils;
import com.webtoonchat.toonchat.domain.chat.Character;
import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;
import com.webtoonchat.toonchat.service.chat.CharacterService;
import com.webtoonchat.toonchat.service.chat.StompMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatApiController {
	private final StompMessageService stompMessageService;
	private final CharacterService characterService;
	@GetMapping("/api/chat/{id}")
	public ResponseEntity<StompMessageEntity> getLastChat(@PathVariable String id) {
		String username = SessionUtils.getUserName();
		Optional<Character> character = characterService.getCharacterInfo(id);
		String characterName = character.map(Character::getBotName).orElse("There is No bot to talk");

		StompMessageEntity lastChat = stompMessageService.getLastChat(username, characterName);
		return ResponseEntity.status(HttpStatus.OK)
				.body(lastChat);
	}

	// @PostMapping("/api/articles")
	// public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
	// 	Article savedArticle = blogService.save(request);
	// 	return ResponseEntity.status(HttpStatus.CREATED)
	// 		.body(savedArticle);
	// }
	// @PostMapping("/api/chat/{characterId}")
	// public ResponseEntity<Chat> sendMessage(@PathVariable Long characterId, @RequestBody AddChatRequest request) {
	// 	System.out.println("request.toString() = " + request.toString());
	// 	System.out.println("characterId = " + characterId);
	// 	// 현재 인증된 사용자 정보를 가져옴
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	// 	String currentUsername = authentication.getName();
	// 	// 메시지를 채팅 기록에 추가
	// 	//Chat chat = chatService.save(request);
	//
	// 	Chat chat = chatService.save(request);
	// 	return ResponseEntity.status(HttpStatus.CREATED)
	// 		.body(chat);
	// }

	// @GetMapping("/api/articles/{id}")
	// public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
	// 	Article article = blogService.findById(id);
	//
	// 	return ResponseEntity.ok()
	// 		.body(new ArticleResponse(article));
	// }
}
