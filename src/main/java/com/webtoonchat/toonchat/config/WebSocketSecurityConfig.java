package com.webtoonchat.toonchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
	@Override
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
		/*
		 * TODO: 로그인 구현되면 Authorization 추가
		 * */
		messages.anyMessage().permitAll();
	}

	@Override
	protected boolean sameOriginDisabled() {
		return true;
	}
}
