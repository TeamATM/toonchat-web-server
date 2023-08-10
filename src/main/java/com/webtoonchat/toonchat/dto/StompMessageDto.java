package com.webtoonchat.toonchat.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class StompMessageDto {
	private String messageId = UUID.randomUUID().toString();
	private String userId;
	private String replyMessageId;
	private String status;
	private String content;
	private String messageFrom;
	private String messageTo;
	private String characterName;
	private Long createdAt = new Date().getTime();
	private Boolean doStream = Boolean.FALSE;

	public StompMessageDto(String status, String content, String messageFrom) {
		this.status = status;
		this.content = content;
		this.messageFrom = messageFrom;
	}

	public StompMessageDto setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public StompMessageDto setReplyMessageId(String replyMessageId) {
		this.replyMessageId = replyMessageId;
		return this;
	}

	public StompMessageDto setReplyMessageId() {
		this.replyMessageId = UUID.randomUUID().toString();
		return this;
	}

	public StompMessageDto setStatus(String status) {
		this.status = status;
		return this;
	}

	public StompMessageDto setContent(String content) {
		this.content = content;
		return this;
	}

	public StompMessageDto setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
		return this;
	}

	public StompMessageDto setMessageTo(String messageTo) {
		this.messageTo = messageTo;
		return this;
	}

	public StompMessageDto setCharacterName(String characterName) {
		this.characterName = characterName;
		return this;
	}

	public StompMessageDto setDoStream(Boolean doStream) {
		this.doStream = doStream;
		return this;
	}

	public CeleryMessageDto toCeleryMessageDto(String task, List<StompMessageEntity> history) {
		GenerationArgsDto generationArgsDto = new GenerationArgsDto();

		return CeleryMessageDto.build(replyMessageId, task)
			.addArgs(new CeleryArgsDto(history,
				userId,
				content,
				messageFrom,
				messageTo,
				characterName,
				generationArgsDto))
			.addArgs(doStream);
	}

	public StompMessageEntity toEntity() {
		StompMessageEntity entity = new StompMessageEntity();
		entity.setMessageId(this.messageId);
		entity.setUserId(this.userId);
		entity.setReplyMessageId(this.replyMessageId);
		entity.setStatus(this.status);
		entity.setContent(this.content);
		entity.setMessageFrom(this.messageFrom);
		entity.setMessageTo(this.messageTo);
		entity.setCharacterName(this.characterName);
		entity.setCreatedAt(this.createdAt);
		entity.setDoStream(this.doStream);
		return entity;
	}
}
