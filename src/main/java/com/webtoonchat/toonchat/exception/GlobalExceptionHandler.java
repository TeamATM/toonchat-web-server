package com.webtoonchat.toonchat.exception;

import java.util.Locale;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.webtoonchat.toonchat.exception.dto.ErrorMessageDto;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final MessageSource messageSource;

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<ErrorMessageDto> handleNoSuchElementFoundException(NoSuchElementException ex, Locale locale) {
		String errorMessage = ex.getMessage();
		if (errorMessage == null) {
			errorMessage = messageSource.getMessage(
				"resource.notfound",
				null,
				"해당 값이 존재하지 않습니다.",
				locale);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(errorMessage));
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorMessageDto> handleIllegalArgumentException(IllegalArgumentException ex, Locale locale) {
		String errorMessage = ex.getMessage();
		if (errorMessage == null) {
			errorMessage = messageSource.getMessage(
				"exception.illegalArgument",
				null,
				"올바르지 않은 요청",
				locale);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(errorMessage));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorMessageDto> handleDefaultException(Exception ex, Locale locale) {
		String errorMessage = ex.getMessage();
		if (errorMessage == null) {
			errorMessage = messageSource.getMessage(
				"exception",
				null,
				"예기치 못한 오류",
				locale);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(errorMessage));
	}
}
