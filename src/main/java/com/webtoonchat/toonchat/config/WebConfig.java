package com.webtoonchat.toonchat.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.webtoonchat.toonchat.resolver.LoginArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/3.52.5/")
				.resourceChain(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/swagger-ui/")
				.setViewName("forward:/swagger-ui/index.html");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:8080", "https://webtoonchat.com",
						"http://localhost:3000", "https://www.webtoonchat.com", "https://dev.webtoonchat.com")
				.allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS", "DELETE")
				.allowCredentials(true);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginArgumentResolver());
	}
}
