package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.chat.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
}
