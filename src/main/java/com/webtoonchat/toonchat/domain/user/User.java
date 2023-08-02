package com.webtoonchat.toonchat.domain.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users") //user는 예약어로 되어있음
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User implements UserDetails { //UserDetails 상속받아 인증 객체로 사용

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@Builder
	public  User(String nickname, String email, String password, String auth) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}

	@Override //권한 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}

	//
	public String getNickname() {
		return nickname;
	}

	@Override //사용자의 id 반환(고윳값)
	public String getUsername() {
		return nickname;
	}

	@Override //사용자 패스워드 반환
	public String getPassword() {
		return password;
	}

	@Override //계정 만료 여부 반환
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override //계정 잠금 여부
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override //패스워드 만료 여부
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override // 계정 사용 가능 여부
	public boolean isEnabled() {
		return true;
	}
}
