package com.webtoonchat.toonchat.domain.member.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
	private final RedisTemplate<String, Object> redisBlackListTemplate;

	public void set(String key, Object ob, int minutes) {
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(ob.toString()));
		redisTemplate.opsForValue().set(key, ob, minutes, TimeUnit.MINUTES);
	}

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public boolean delete(String key) {
		return redisTemplate.delete(key);
	}

	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public void setBlackList(String key, Object ob, Long milliSeconds) {
		redisBlackListTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(ob.toString()));
		redisBlackListTemplate.opsForValue().set(key, ob, milliSeconds, TimeUnit.MILLISECONDS);
	}

	public Object getBlackList(String key) {
		return redisBlackListTemplate.opsForValue().get(key);
	}

	public boolean deleteBlackList(String key) {
		return redisBlackListTemplate.delete(key);
	}

	public boolean hasKeyBlackList(String key) {
		return redisBlackListTemplate.hasKey(key);
	}
}
