package com.webtoonchat.toonchat.controller.chat;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.chat.Chat;
import com.webtoonchat.toonchat.dto.chat.AddChatRequest;
import com.webtoonchat.toonchat.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatApiController {
	private final ChatService chatService;
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
