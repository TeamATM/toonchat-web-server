package com.webtoonchat.toonchat.security.jwt.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.webtoonchat.toonchat.security.jwt.exception.JwtExceptionCode;
import com.webtoonchat.toonchat.security.jwt.token.JwtAuthenticationToken;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		String token = "";
		try {
			token = getToken(request);
			if (StringUtils.hasText(token)) {
				getAuthentication(token);
			}
		} catch (NullPointerException | IllegalStateException e) {
			request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
			log.error("Not found Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new not found token exception");
		} catch (SecurityException | MalformedJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
			log.error("Invalid Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new invalid token exception");
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
			log.error("EXPIRED Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new expired token exception");
		} catch (UnsupportedJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
			log.error("Unsupported Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new unsupported token exception");
		} catch (Exception e) {
			log.error("====================================================");
			log.error("JwtFilter - doFilterInternal() 오류 발생");
			log.error("token : {}", token);
			log.error("Exception Message : {}", e.getMessage());
			log.error("Exception StackTrace : {");
			e.printStackTrace();
			log.error("}");
			log.error("====================================================");
			throw new BadCredentialsException("throw new exception");
		}

		/**
		 * TODO: 주어진 accessToken이 blacklist에 등록되어있는지 redis에서 확인
		 * blacklist에 등록되어 있다면 throw exception이나 return.
		 */
		filterChain.doFilter(request, response);
	}

	private void getAuthentication(String token) {
		JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext()
				.setAuthentication(authenticate);
	}

	private String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer")) {
			String[] arr = authorization.split(" ");
			return arr[1];
		}
		return null;
	}
}
