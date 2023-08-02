package com.webtoonchat.toonchat.controller.chat;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.webtoonchat.toonchat.domain.chat.Chat;
import com.webtoonchat.toonchat.service.chat.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatViewController {
	private final ChatService chatService;
	@GetMapping("/chat/{character_id}")
	public String showChatPage(@PathVariable Long characterId, Model model) {
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();

		// 현재 사용자의 채팅 기록을 조회
		List<Chat> userChatHistory = chatService.getUserChatHistory(currentUsername);
		System.out.println("showchatpage");
		model.addAttribute("userChatHistory", userChatHistory);
		model.addAttribute("username", currentUsername);
		System.out.println("userChatHistory = " + userChatHistory);
		System.out.println("currentUsername = " + currentUsername);
		return "chatPage";
	}
}
