package com.webtoonchat.toonchat.config;

import java.util.Objects;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.webtoonchat.toonchat.config.handler.StompInboundHandler;
import com.webtoonchat.toonchat.config.handler.StompOutboundHandler;
import com.webtoonchat.toonchat.config.security.AnonymousHandshakeInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class StompConfig
	implements WebSocketMessageBrokerConfigurer {
	private final Environment environment;
	private final StompInboundHandler stompInboundHandler;
	private final StompOutboundHandler stompOutboundHandler;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*
		 * TODO: CORS 관련 설정
		 * */
		registry.addEndpoint("ws")
			.setAllowedOriginPatterns("*")
			.addInterceptors(new AnonymousHandshakeInterceptor())
			.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/chat");
		registry.enableStompBrokerRelay("/topic", "/direct", "/exchange")
			.setRelayHost(Objects.requireNonNull(environment.getProperty("spring.stomp.broker.relay-host")))
			.setSystemLogin(Objects.requireNonNull(environment.getProperty("spring.stomp.broker.system-username")))
			.setSystemPasscode(Objects.requireNonNull(environment.getProperty("spring.stomp.broker.system-password")))
			.setClientLogin(Objects.requireNonNull(environment.getProperty("spring.stomp.broker.client-username")))
			.setClientPasscode(Objects.requireNonNull(environment.getProperty("spring.stomp.broker.client-password")));
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompInboundHandler);
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompOutboundHandler);
	}
}
