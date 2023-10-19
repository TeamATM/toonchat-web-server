package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.controller.dto.CharacterRegisterDto;
import com.webtoonchat.toonchat.controller.dto.CharacterResponseDto;
import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.domain.FriendshipId;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId> {
	@Query("select new com.webtoonchat.toonchat.controller.dto.CharacterResponseDto("
		+ "c.id, c.characterName, c.profileImageUrl, c.backgroundImageUrl, c.statusMessage, c.hashTag) "
		+ "from Friendship f join f.character c where f.member.id = :memberId")
	List<CharacterResponseDto> findFriendsByMemberId(Long memberId);
}

