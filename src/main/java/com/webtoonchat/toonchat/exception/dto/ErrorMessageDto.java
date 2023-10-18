package com.webtoonchat.toonchat.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
	private Object error;
}
