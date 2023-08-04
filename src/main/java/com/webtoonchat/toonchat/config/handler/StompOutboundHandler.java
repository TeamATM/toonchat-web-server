package com.webtoonchat.toonchat.config.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoonchat.toonchat.dto.StompMessageDto;

import lombok.RequiredArgsConstructor;

@Component
public class StompOutboundHandler implements ChannelInterceptor {
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

		StompCommand command = accessor.getCommand();
		if (StompCommand.MESSAGE.equals(command)) {
			String jsonPayload = new String((byte[]) message.getPayload());
			StompMessageDto stompMessageDto = convertMessageToStompDto(jsonPayload);

			// Insert into database
		}
	}

	public StompMessageDto convertMessageToStompDto(String payload) {
		try {
			return objectMapper.readValue(payload, StompMessageDto.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error deserializing JSON payload", e);
		}
	}
}
