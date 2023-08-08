package com.webtoonchat.toonchat.config.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AnonymousAuthentication extends AbstractAuthenticationToken {
	private final String username;

	public AnonymousAuthentication(String username) {
		super(Collections.emptyList());
		this.username = username;
		super.setAuthenticated(true); // Set this to true since it's an anonymous authentication
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}
}

