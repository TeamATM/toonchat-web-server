package com.webtoonchat.toonchat.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.webtoonchat.toonchat.security.jwt.exception.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

// Spring Security 설정.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationManagerConfig authenticationManagerConfig;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin().disable() // 직접 id, password를 입력받아서 JWT토큰을 리턴하는 API를 직접 만든다.
				.csrf().disable() // CSRF는 Cross Site Request Forgery의 약자. CSRF공격을 막기 위한 방법.
				.cors() //.configurationSource(corsConfigurationSource())
				.and()
				.apply(authenticationManagerConfig)
				.and()
				.httpBasic().disable()
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.requestMatchers("/members/signup", "/members/login", "/members/refreshToken").permitAll()
				.requestMatchers("/swagger/**", "/v3/api-docs/**", "/health").permitAll()
				.requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/**").hasAnyRole("USER", "ADMIN")
				.anyRequest().hasAnyRole("USER", "ADMIN")
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.build();
	}


	// <<Advanced>> Security Cors로 변경 시도
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH", "OPTION", "PUT"));
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	// 암호를 암호화하거나, 사용자가 입력한 암호가 기존 암호랑 일치하는지 검사할 때 이 Bean을 사용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
