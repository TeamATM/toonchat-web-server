package com.webtoonchat.toonchat.domain.member.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name = "member")
@NoArgsConstructor
@Setter
@Getter
public class Member {
	@Id // 이 필드가 Table의 PK.
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY) // userId는 자동으로 생성
	private Long id;

	@Column
	private String provider;

	@Column
	private String profileUrl;

	@Column(length = 255)
	private String email;

	@Column(length = 50)
	private String name;

	@Column(length = 500)
	private String password;

	@CreatedDate
	private LocalDateTime createdAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "member_role",
			joinColumns = @JoinColumn(name = "member_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();
	public void addRole(Role role) {
		roles.add(role);
	}

	@OneToMany(mappedBy = "member")
	private List<Friendship> friendships = new ArrayList<>();  // 수정된 부분
}
