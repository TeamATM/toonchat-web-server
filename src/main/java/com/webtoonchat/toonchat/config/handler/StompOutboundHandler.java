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

import com.webtoonchat.toonchat.dto.StompMessageDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StompOutboundHandler implements ChannelInterceptor {
	private StringMessageConverter converter = new StringMessageConverter();
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

		StompCommand command = accessor.getCommand();
		System.out.println("command = " + command);
		System.out.println("message = " + new String((byte[]) message.getPayload()));
		System.out.println("command = " + command);
		if (StompCommand.MESSAGE.equals(command)) {
			// Handle CONNECT command
			System.out.println("123456789");
		}
	}

}
