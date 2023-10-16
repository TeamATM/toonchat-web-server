package com.webtoonchat.toonchat.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.Characters;
import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.repository.FriendshipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FriendshipService {

	private final FriendshipRepository friendshipRepository;

	public List<Friendship> getFriendshipsByMemberId(Long memberId) {
		return friendshipRepository.findByMemberMemberId(memberId);
	}

	public List<Friendship> getAll() {
		return friendshipRepository.findAll();
	}

	public Optional<Friendship> getById(Long id) {
		return friendshipRepository.findById(id);
	}

	public Friendship create(Friendship friendship) {
		return friendshipRepository.save(friendship);
	}

	public void delete(Long id) {
		if (friendshipRepository.existsById(id)) {
			friendshipRepository.deleteById(id);
		}
	}

	@Transactional
	public Friendship createFriendship(Member member, Characters charac) {
		// 친구 관계 생성
		Friendship friendship = new Friendship();
		friendship.setMember(member);
		friendship.setCharacters(charac);

		return friendshipRepository.save(friendship);
	}


}
