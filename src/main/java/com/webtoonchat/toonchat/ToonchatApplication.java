package com.webtoonchat.toonchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(servers = {@Server(url = "https://dev.webtoonchat.com"), @Server(url = "http://localhost:8080")})
@EnableJpaAuditing
@SpringBootApplication
public class ToonchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToonchatApplication.class, args);
	}
}
