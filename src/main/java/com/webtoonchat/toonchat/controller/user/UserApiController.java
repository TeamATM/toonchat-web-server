package com.webtoonchat.toonchat.controller.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.atm.toonchat.dto.AddUserRequest;
import com.atm.toonchat.service.UserDetailService;
import com.atm.toonchat.service.UserService;
import com.webtoonchat.toonchat.dto.user.AddUserRequest;
import com.webtoonchat.toonchat.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserApiController {

	private final UserService userService;

	@PostMapping("/user")
	public String signup(AddUserRequest request) {
		userService.save(request);
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
			SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
}
