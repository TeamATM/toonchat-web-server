package com.webtoonchat.toonchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.chat.Intimacy;

@Repository
public interface IntimacyRepository extends JpaRepository<Intimacy, Long> {
	Optional<Intimacy> findByUserName(String username);
}

