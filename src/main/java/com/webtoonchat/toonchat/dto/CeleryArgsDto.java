package com.webtoonchat.toonchat.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webtoonchat.toonchat.domain.chat.StompMessageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CeleryArgsDto {
	/*
	 * Spring에서 Celery로 작업을 넘겨줄때 필요한 정보
	 * */
	private List<StompMessageEntity> history;
	private String userId;
	private String content;
	private String messageFrom;
	private String messageTo;
	private String characterName;
	@JsonProperty("generation_args")
	private GenerationArgsDto generationArgsDto;
}
