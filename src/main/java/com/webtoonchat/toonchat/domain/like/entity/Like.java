package com.webtoonchat.toonchat.domain.like.entity;

import com.webtoonchat.toonchat.domain.board.entity.Board;
import com.webtoonchat.toonchat.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "\"like\"") //이거 이름 안바꿔 주면 충돌남 SQL 예약어라서..
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
	public Like(Member member, Board board) {
		this.member = member;
		this.board = board;
	}
}
