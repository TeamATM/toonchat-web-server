package com.webtoonchat.toonchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findByMemberId(Long memberId);

	Optional<Member> findByEmailAndProvider(String email, String provider);

	boolean existsByEmailAndProvider(String email, String provider);
	/**
	 * TO-Do : findByEmailAndProvider 추가
	 */
}
