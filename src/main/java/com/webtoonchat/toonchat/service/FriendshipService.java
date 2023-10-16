package com.webtoonchat.toonchat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.repository.FriendshipRepository;


@Service
public class FriendshipService {

	private final FriendshipRepository friendshipRepository;

	public FriendshipService(FriendshipRepository friendshipRepository) {
		this.friendshipRepository = friendshipRepository;
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
}
