package com.webtoonchat.toonchat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.domain.FriendshipId;
import com.webtoonchat.toonchat.domain.Member;
import com.webtoonchat.toonchat.repository.FriendshipRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FriendshipService {

	private final FriendshipRepository friendshipRepository;
	private final MemberService memberService;
	private final CharacterService characterService;

	public List<Character> getFriendsByMemberId(Long memberId) {
		return friendshipRepository.findFriendsByMemberId(memberId);
	}

	public Friendship create(Friendship friendship) {
		return friendshipRepository.save(friendship);
	}

	public void delete(Long memberId, Long characterId) {
		FriendshipId friendshipId = new FriendshipId(memberId, characterId);
		if (friendshipRepository.existsById(friendshipId)) {
			friendshipRepository.deleteById(friendshipId);
		}
	}

	@Transactional
	public Friendship createFriendship(Long memberId, Long characterId) {
		Member member = memberService.getMemberReference(memberId);
		Character character = characterService.getCharacterById(characterId);
		// 친구 관계 생성
		FriendshipId friendshipId = new FriendshipId(member.getId(), character.getId());
		Friendship friendship = new Friendship(friendshipId, member, character);

		return friendshipRepository.save(friendship);
	}
}
