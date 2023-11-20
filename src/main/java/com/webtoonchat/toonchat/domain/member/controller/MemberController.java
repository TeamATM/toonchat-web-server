package com.webtoonchat.toonchat.domain.member.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.member.dto.MemberLoginDto;
import com.webtoonchat.toonchat.domain.member.dto.MemberLoginResponseDto;
import com.webtoonchat.toonchat.domain.member.dto.MemberLogoutRequestDto;
import com.webtoonchat.toonchat.domain.member.dto.MemberSignupDto;
import com.webtoonchat.toonchat.domain.member.dto.MemberSignupResponseDto;
import com.webtoonchat.toonchat.domain.member.entity.Member;
import com.webtoonchat.toonchat.domain.member.entity.Role;
import com.webtoonchat.toonchat.domain.member.repository.MemberRepository;
import com.webtoonchat.toonchat.domain.member.service.MemberService;
import com.webtoonchat.toonchat.domain.member.util.RedisUtil;
import com.webtoonchat.toonchat.domain.token.dto.RefreshTokenDto;
import com.webtoonchat.toonchat.domain.token.entity.RefreshToken;
import com.webtoonchat.toonchat.domain.token.service.RefreshTokenService;
import com.webtoonchat.toonchat.resolver.annotation.Login;
import com.webtoonchat.toonchat.security.jwt.util.JwtTokenizer;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/members")
public class MemberController {

	private final MemberRepository memberRepository;
	private final JwtTokenizer jwtTokenizer;
	private final MemberService memberService;
	private final RefreshTokenService refreshTokenService;
	private final PasswordEncoder passwordEncoder;
	private final RedisUtil redisUtil;

	@Operation(description = "소셜 로그인")
	@PostMapping("/social-login")
	public ResponseEntity<MemberLoginResponseDto> socialSignupOrLogin(
			@RequestBody @Validated MemberSignupDto memberSignupDto) {
		/**
		 * 가입된 이력 없을 때
		 */
		if (!memberRepository.existsByEmailAndProvider(memberSignupDto.getEmail(), memberSignupDto.getProvider())) {
			Member member = new Member();
			member.setName(memberSignupDto.getName());
			member.setEmail(memberSignupDto.getEmail());
			member.setProvider(memberSignupDto.getProvider());
			member.setProfileUrl("defaultProfileUrl.png");
			memberService.addMember(member);
		}
		Member foundMember = memberService.findByEmailAndProviderWithRoles(
				memberSignupDto.getEmail(), memberSignupDto.getProvider());
		List<String> roles = foundMember.getRoles().stream().map(Role::getName).collect(Collectors.toList());

		String accessToken = jwtTokenizer.createAccessToken(
				foundMember.getProvider(), foundMember.getId(), foundMember.getEmail(), roles);
		String refreshToken = jwtTokenizer.createRefreshToken(
				foundMember.getProvider(), foundMember.getId(), foundMember.getEmail(), roles);

		RefreshToken refreshTokenEntity = new RefreshToken();
		refreshTokenEntity.setValue(refreshToken);
		refreshTokenEntity.setMemberId(foundMember.getId());
		refreshTokenService.addRefreshToken(refreshTokenEntity);

		MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.memberId(foundMember.getId())
				.nickname(foundMember.getName())
				.profileUrl(foundMember.getProfileUrl())
				.provider(foundMember.getProvider())
				.build();
		return ResponseEntity.ok(loginResponse);
	}

	@Operation(description = "일반 회원가입")
	@PostMapping("/signup")
	public ResponseEntity<MemberSignupResponseDto> signup(@RequestBody @Validated MemberSignupDto memberSignupDto) {
		/**
		 * TODO : 기본 프로필 url set, provider set
		 *  로그인 중복 처리(완)
		 */
		if (memberRepository.existsByEmailAndProvider(memberSignupDto.getEmail(), memberSignupDto.getProvider())) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		Member member = new Member();
		member.setName(memberSignupDto.getName());
		member.setEmail(memberSignupDto.getEmail());
		member.setProvider(memberSignupDto.getProvider());
		member.setProfileUrl("defaultProfileUrl.png");
		if (memberSignupDto.getPassword() != null) {
			member.setPassword(passwordEncoder.encode(memberSignupDto.getPassword()));
		}
		Member saveMember = memberService.addMember(member);

		MemberSignupResponseDto memberSignupResponse = new MemberSignupResponseDto();
		memberSignupResponse.setMemberId(saveMember.getId());
		memberSignupResponse.setName(saveMember.getName());
		memberSignupResponse.setCreatedAt(saveMember.getCreatedAt());
		memberSignupResponse.setEmail(saveMember.getEmail());
		memberSignupResponse.setProfileUrl(saveMember.getProfileUrl());
		memberSignupResponse.setProvider(saveMember.getProvider());

		// 회원가입
		return ResponseEntity.status(HttpStatus.CREATED).body(memberSignupResponse);
	}

	@Operation(description = "일반 로그인")
	@PostMapping("/login")
	public ResponseEntity<MemberLoginResponseDto> login(@RequestBody @Validated MemberLoginDto loginDto) {
		log.debug("login start");
		// email이 없을 경우 Exception이 발생한다. Global Exception에 대한 처리가 필요하다.
		/**
		 * TO-Do : findByEmail -> findByEmailAndProvider  변경하기
		 */
		Member member = memberService.findByEmailAndProviderWithRoles(loginDto.getEmail(), loginDto.getProvider());
		if (member.getPassword() != null) {
			if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
		}
		// List<Role> ===> List<String>
		List<String> roles = member.getRoles().stream().map(Role::getName).collect(Collectors.toList());

		// JWT토큰을 생성하였다. jwt라이브러리를 이용하여 생성.
		String accessToken = jwtTokenizer.createAccessToken(
				member.getProvider(), member.getId(), member.getEmail(), roles);
		String refreshToken = jwtTokenizer.createRefreshToken(
				member.getProvider(), member.getId(), member.getEmail(), roles);

		/**
		 * RefreshToken을 DB에 저장한다.
		 * TO-Do : 성능 때문에 DB가 아니라 Redis에 저장하는 것이 좋다.
		 */
		RefreshToken refreshTokenEntity = new RefreshToken();
		refreshTokenEntity.setValue(refreshToken);
		refreshTokenEntity.setMemberId(member.getId());
		refreshTokenService.addRefreshToken(refreshTokenEntity);

		/**
		 * TO-Do : 프로필 url(완), provider response에 담기(완)
		 */
		MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.memberId(member.getId())
				.nickname(member.getName())
				.profileUrl(member.getProfileUrl())
				.provider(member.getProvider())
				.build();
		return ResponseEntity.ok(loginResponse);
	}

	@Operation(description = "로그아웃")
	@PostMapping("/logout")
	public ResponseEntity logout(@RequestBody @Validated MemberLogoutRequestDto logoutDto, @Login Claims claims) {
		/**
		 * TODO: Redis에 accessToken blacklist 등록하기 + ttl설정(유효기간까지), filter단에서 인가하기 전에 blacklist 등록되어있는지 확인
		 */
		Long userId = claims.get("userId", Long.class);
		String accessToken = logoutDto.getToken();
		Claims accessTokenClaims = jwtTokenizer.parseAccessToken(accessToken);
		Long ttl  = accessTokenClaims.getExpiration().getTime() - System.currentTimeMillis();
		redisUtil.setBlackList(accessToken, "access_token", ttl);
		refreshTokenService.deleteRefreshToken(userId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	/*
	1. 전달받은 유저의 아이디로 유저가 존재하는지 확인한다.
	2. RefreshToken이 유효한지 체크한다.
	3. AccessToken을 발급하여 기존 RefreshToken과 함께 응답한다.
	 */
	@Operation(description = "access token 재발급")
	@PostMapping("/refreshtoken")
	public ResponseEntity<MemberLoginResponseDto> requestRefresh(@RequestBody RefreshTokenDto refreshTokenDto) {
		RefreshToken refreshToken = refreshTokenService.findRefreshToken(
				refreshTokenDto.getRefreshToken()).orElseThrow(() ->
				new IllegalArgumentException("Refresh token not found"));
		Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());

		String provider = String.valueOf((String)claims.get("provider"));
		Long userId = Long.valueOf((Integer) claims.get("userId"));

		Member member = memberService.findByMemberId(userId);

		List roles = (List) claims.get("roles");
		String email = claims.getSubject();
		/**
		 * TO-Do : 프로필 url, provider response에 담기
		 */
		String accessToken = jwtTokenizer.createAccessToken(provider, userId, email, roles);

		MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshTokenDto.getRefreshToken())
				.memberId(member.getId())
				.nickname(member.getName())
				.profileUrl(member.getProfileUrl())
				.provider(member.getProvider())
				.build();
		return ResponseEntity.ok().body(loginResponse);
	}

}

