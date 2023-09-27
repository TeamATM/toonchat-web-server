package com.webtoonchat.toonchat.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.domain.RefreshToken;
import com.webtoonchat.toonchat.domain.Role;
import com.webtoonchat.toonchat.dto.MemberLoginDto;
import com.webtoonchat.toonchat.dto.MemberLoginResponseDto;
import com.webtoonchat.toonchat.dto.MemberSignupDto;
import com.webtoonchat.toonchat.dto.MemberSignupResponseDto;
import com.webtoonchat.toonchat.dto.RefreshTokenDto;
import com.webtoonchat.toonchat.repository.MemberRepository;
import com.webtoonchat.toonchat.security.jwt.util.JwtTokenizer;
import com.webtoonchat.toonchat.service.MemberService;
import com.webtoonchat.toonchat.service.RefreshTokenService;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody @Valid MemberSignupDto memberSignupDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
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
		member.setPassword(passwordEncoder.encode(memberSignupDto.getPassword()));

		Member saveMember = memberService.addMember(member);

		MemberSignupResponseDto memberSignupResponse = new MemberSignupResponseDto();
		memberSignupResponse.setMemberId(saveMember.getMemberId());
		memberSignupResponse.setName(saveMember.getName());
		memberSignupResponse.setRegdate(saveMember.getRegdate());
		memberSignupResponse.setEmail(saveMember.getEmail());

		// 회원가입
		return new ResponseEntity(memberSignupResponse, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		// email이 없을 경우 Exception이 발생한다. Global Exception에 대한 처리가 필요하다.
		/**
		 * TO-Do : findByEmail -> findByEmailAndProvider  변경하기
		 */
		Member member = memberService.findByEmail(loginDto.getEmail());
		if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		// List<Role> ===> List<String>
		List<String> roles = member.getRoles().stream().map(Role::getName).collect(Collectors.toList());

		// JWT토큰을 생성하였다. jwt라이브러리를 이용하여 생성.
		String accessToken = jwtTokenizer.createAccessToken(member.getMemberId(), member.getEmail(), roles);
		String refreshToken = jwtTokenizer.createRefreshToken(member.getMemberId(), member.getEmail(), roles);

		/**
		 * RefreshToken을 DB에 저장한다.
		 * TO-Do : 성능 때문에 DB가 아니라 Redis에 저장하는 것이 좋다.
		 */
		RefreshToken refreshTokenEntity = new RefreshToken();
		refreshTokenEntity.setValue(refreshToken);
		refreshTokenEntity.setMemberId(member.getMemberId());
		refreshTokenService.addRefreshToken(refreshTokenEntity);

		/**
		 * TO-Do : 프로필 url(완), provider response에 담기(완)
		 */
		MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.memberId(member.getMemberId())
				.nickname(member.getName())
				.profileUrl(member.getProfileUrl())
				.provider(member.getProvider())
				.build();
		return new ResponseEntity(loginResponse, HttpStatus.OK);
	}

	@DeleteMapping("/logout")
	public ResponseEntity logout(@RequestBody RefreshTokenDto refreshTokenDto) {
		/**
		 * TODO: Redis에 accessToken blacklist 등록하기 + ttl설정(유효기간까지), filter단에서 인가하기 전에 blacklist 등록되어있는지 확인
		 */
		refreshTokenService.deleteRefreshToken(refreshTokenDto.getRefreshToken());
		return new ResponseEntity(HttpStatus.OK);
	}

	/*
	1. 전달받은 유저의 아이디로 유저가 존재하는지 확인한다.
	2. RefreshToken이 유효한지 체크한다.
	3. AccessToken을 발급하여 기존 RefreshToken과 함께 응답한다.
	 */
	@PostMapping("/refreshToken")
	public ResponseEntity requestRefresh(@RequestBody RefreshTokenDto refreshTokenDto) {
		RefreshToken refreshToken = refreshTokenService.findRefreshToken(
				refreshTokenDto.getRefreshToken()).orElseThrow(() ->
				new IllegalArgumentException("Refresh token not found"));
		Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());

		Long userId = Long.valueOf((Integer) claims.get("userId"));

		Member member = memberService.getMember(userId).orElseThrow(
				() -> new IllegalArgumentException("Member not found"));


		List roles = (List) claims.get("roles");
		String email = claims.getSubject();
		/**
		 * TO-Do : 프로필 url, provider response에 담기
		 */
		String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);

		MemberLoginResponseDto loginResponse = MemberLoginResponseDto.builder()
				.accessToken(accessToken)
				.refreshToken(refreshTokenDto.getRefreshToken())
				.memberId(member.getMemberId())
				.nickname(member.getName())
				.build();
		return new ResponseEntity(loginResponse, HttpStatus.OK);
	}
}

