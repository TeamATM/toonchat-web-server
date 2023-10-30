package com.webtoonchat.toonchat;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.annotation.PostConstruct;

@OpenAPIDefinition(servers = {@Server(url = "https://dev.webtoonchat.com"), @Server(url = "http://localhost:8080")})
@EnableJpaAuditing
@SpringBootApplication
public class ToonchatApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ToonchatApplication.class, args);
	}
}
