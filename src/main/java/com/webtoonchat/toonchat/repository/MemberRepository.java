package com.webtoonchat.toonchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webtoonchat.toonchat.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	Optional<Member> findByEmailAndProvider(String email, String provider);

	@Query("select m from Member m join fetch m.roles where m.email = :email and m.provider = :provider")
	Optional<Member> findByEmailAndProviderWithRoles(String email, String provider);

	boolean existsByEmailAndProvider(String email, String provider);
	/**
	 * TO-Do : findByEmailAndProvider 추가
	 */
}
