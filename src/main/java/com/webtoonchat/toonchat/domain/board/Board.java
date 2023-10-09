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
	private int writerId;

	@Column(name = "bgno", nullable = false)
	private String bgno;

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
			int writerId, String title, String content, String bgno, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.title = title;
		this.content = content;
		this.bgno = bgno;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.writerId = writerId;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
