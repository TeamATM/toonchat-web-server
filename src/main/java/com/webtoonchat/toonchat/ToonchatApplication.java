package com.webtoonchat.toonchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(servers = {@Server(url = "https://dev.webtoonchat.com")})
@EnableJpaAuditing
@EnableMongoAuditing
@EnableMongoRepositories
@SpringBootApplication
public class ToonchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToonchatApplication.class, args);
	}
}
