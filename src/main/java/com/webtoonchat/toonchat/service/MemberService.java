package com.webtoonchat.toonchat.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.domain.Role;
import com.webtoonchat.toonchat.repository.MemberRepository;
import com.webtoonchat.toonchat.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
	}

	@Transactional
	public Member addMember(Member member) {
		Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
		member.addRole(userRole.get());
		Member saveMember = memberRepository.save(member);
		return saveMember;
	}

	@Transactional(readOnly = true)
	public Optional<Member> getMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
