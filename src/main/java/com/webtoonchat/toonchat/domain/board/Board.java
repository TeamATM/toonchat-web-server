package com.webtoonchat.toonchat.domain.board;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "writer", nullable = false)
	private String writer;

	@Column(name = "writer_id", nullable = false)
	private Long writerId;

	@Column(name = "character_id", nullable = false)
	private Long characterId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false)
	private String content;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Builder // 빌더 패턴으로 객체 생성
	public Board(
			String writer, String title, String content, Long writerId,
			Long characterId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.title = title;
		this.content = content;
		this.characterId = characterId;
		this.createdAt = createdAt;
		this.updatedAt = createdAt;
		this.writer = writer;
		this.writerId = writerId;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
		this.updatedAt = LocalDateTime.now();
	}
}
