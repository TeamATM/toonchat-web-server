package com.webtoonchat.toonchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.Character;
import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.domain.FriendshipId;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipId> {
	@Query("select c from Friendship f join f.character c where f.member.id = :memberId")
	List<Character> findFriendsByMemberId(Long memberId);
}

