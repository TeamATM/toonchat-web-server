package com.webtoonchat.toonchat.config.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.webtoonchat.toonchat.SessionUtils;

@Component
public class StompInboundHandler implements ChannelInterceptor {
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
			// System.out.println("user = " + SessionUtils.getUserName());
			/**
			 * 유저 이름으로 Rabbitmq 라우팅 키 설정
			 * TODO: Anonymous 유저는 어떻게?
			 */
			accessor.setDestination("/topic/" + SessionUtils.getUserName());

			return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
		}

		return message;
	}
}
