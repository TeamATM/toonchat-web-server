package com.webtoonchat.toonchat.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Data;

@Data
public class CeleryMessageDto {
	/*
	 * TODO: Celery Message Protocol v2로 바꾸기
	 * */
	private String id;
	private String task;
	private List<Object> args = new ArrayList<>();
	private Map<String, Object> kwargs = new HashMap<>();

	private CeleryMessageDto(String id, String task) {
		this.id = id;
		this.task = task;
	}

	public static CeleryMessageDto build(String id, String task) {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
		return new CeleryMessageDto(id, task);
	}

	public CeleryMessageDto addArgs(Object arg) {
		args.add(arg);
		return this;
	}

	public CeleryMessageDto addKwargs(String key, Object value) {
		kwargs.put(key, value);
		return this;
	}
}
