package com.webtoonchat.toonchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CeleryArgsDto {
	/*
	 * Spring에서 Celery로 작업을 넘겨줄때 필요한 정보
	 * */
	private String history;
	private String content;
	private String messageFrom;
	private String messageTo;
	private String characterName;
}
