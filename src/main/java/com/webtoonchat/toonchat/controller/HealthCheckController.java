package com.webtoonchat.toonchat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
	private final ResponseEntity<String> healthResponseEntity = ResponseEntity.ok().body("OK");
	@GetMapping
	public ResponseEntity<String> health() {
		return healthResponseEntity;
	}
}
