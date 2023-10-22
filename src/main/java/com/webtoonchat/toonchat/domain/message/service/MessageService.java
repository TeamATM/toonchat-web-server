package com.webtoonchat.toonchat.domain.message.service;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.message.dto.CharacterUpdateMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
	private final RabbitTemplate rabbitTemplate;

	private final Exchange exchange;

	@Value("${rabbit.routing.key.character}")
	private String characterUpdateRoutingKey;

	public void sendCharacterUpdateMessage(CharacterUpdateMessageDto characterMessage) {
		rabbitTemplate.convertAndSend(exchange.getName(), characterUpdateRoutingKey, characterMessage);
		log.info("new character created");
	}
}
