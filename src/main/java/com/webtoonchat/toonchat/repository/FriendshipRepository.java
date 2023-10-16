package com.webtoonchat.toonchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtoonchat.toonchat.domain.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}

