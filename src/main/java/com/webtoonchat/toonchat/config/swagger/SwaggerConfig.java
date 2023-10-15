package com.webtoonchat.toonchat.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@SecurityScheme(
	type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER, name = "Authorization"
)
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.components(new Components())
			.addSecurityItem(new SecurityRequirement().addList("Authorization"))
			.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
				.title("Springdoc 테스트")
				.description("Springdoc을 사용한 Swagger UI 테스트")
				.version("1.0.0");
	}
}
