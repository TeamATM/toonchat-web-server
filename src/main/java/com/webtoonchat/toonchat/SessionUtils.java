package com.webtoonchat.toonchat;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUtils {
	public static String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof String) {
			return (String)principal;
		} else if (principal instanceof User) {
			return ((User)principal).getUsername();
		} else {
			throw new UsernameNotFoundException("No Username");
		}
	}
}
