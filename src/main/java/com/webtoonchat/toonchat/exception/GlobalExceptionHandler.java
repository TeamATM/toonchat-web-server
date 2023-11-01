package com.webtoonchat.toonchat.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.webtoonchat.toonchat.exception.dto.ErrorMessageDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final MessageSource messageSource;

	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<ErrorMessageDto> handleNoSuchElementFoundException(
		HttpServletRequest request,
		NoSuchElementException ex,
		Locale locale) {
		String errorMessage = ex.getMessage();
		logError(request, errorMessage, ex);
		if (errorMessage == null) {
			errorMessage = messageSource.getMessage(
				"exception.noSuchElement",
				null,
				"해당 값이 존재하지 않습니다.",
				locale);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(errorMessage));
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorMessageDto> handleIllegalArgumentException(
		HttpServletRequest request,
		IllegalArgumentException ex,
		Locale locale) {
		String errorMessage = ex.getMessage();
		logError(request, errorMessage, ex);
		if (errorMessage == null) {
			errorMessage = messageSource.getMessage("exception.illegalArgument", null, "올바르지 않은 요청", locale);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageDto(errorMessage));
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorMessageDto> handleDefaultException(
		HttpServletRequest request,
		Exception ex,
		Locale locale) {

		logError(request, ex.getMessage(), ex);
		log.error("Unhandled error", ex);

		String errorMessage = messageSource.getMessage("exception", null, "예기치 못한 오류", locale);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessageDto(errorMessage));
	}

	@ExceptionHandler({BindException.class})
	public ResponseEntity<ErrorMessageDto> handleDefaultException(
		HttpServletRequest request,
		BindException ex,
		Locale locale) {
		logError(request, ex.getMessage(), ex);
		Map<String, String> errorResults = new HashMap<>();
		for (ObjectError error : ex.getGlobalErrors()) {
			errorResults.put(error.getObjectName(), messageSource.getMessage(error, locale));
		}
		for (FieldError fieldError : ex.getFieldErrors()) {
			errorResults.put(fieldError.getField(), messageSource.getMessage(fieldError, locale));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessageDto(errorResults));
	}

	private void logError(HttpServletRequest request, String errorMessage, Exception ex) {
		log.warn("remoteAddress={}, requestURI={}, method={}, message={}, exception={}",
			request.getRemoteHost(),
			request.getRequestURI(),
			request.getMethod(),
			errorMessage,
			ex.getClass());
	}
}
