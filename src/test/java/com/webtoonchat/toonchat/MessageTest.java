package com.webtoonchat.toonchat;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoonchat.toonchat.domain.message.dto.CharacterMessageOp;
import com.webtoonchat.toonchat.domain.message.dto.CharacterUpdateMessageDto;

public class MessageTest {

	@Test
	void messageConvertTest() throws IOException {
		Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
		CharacterUpdateMessageDto characterUpdateMessageDto = new CharacterUpdateMessageDto(
			CharacterMessageOp.UPDATE,
			1L,
			"김미소",
			"/",
			"/",
			"상태메시지",
			"#해시태그",
			"persona1");

		Message message = messageConverter.toMessage(characterUpdateMessageDto, null, null);
		System.out.println(new String(message.getBody()));
		ObjectMapper objectMapper = new ObjectMapper();
		MappedMessage mappedMessage = objectMapper.readValue(message.getBody(), MappedMessage.class);
		Assertions.assertThat(mappedMessage.id).isEqualTo(1);
		Assertions.assertThat(mappedMessage.op).isEqualTo(CharacterMessageOp.UPDATE);
	}

	static class MappedMessage {
		@JsonProperty("op")
		CharacterMessageOp op;
		@JsonProperty("_id")
		Long id;
		@JsonProperty("characterName")
		String characterName;
		@JsonProperty("profileImageUrl")
		String profileImageUrl;
		@JsonProperty("backgroundImageUrl")
		String backgroundImageUrl;
		@JsonProperty("statusMessage")
		String statusMessage;
		@JsonProperty("hashTag")
		String hashTag;
		@JsonProperty("persona")
		String persona;
	}
}
