package com.webtoonchat.toonchat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtoonchat.toonchat.domain.Friendship;
import com.webtoonchat.toonchat.service.FriendshipService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friendships")
public class FriendshipController {

	private final FriendshipService friendshipService;

	@GetMapping("")
	public ResponseEntity<List<Friendship>> getAll() {
		List<Friendship> friendships = friendshipService.getAll();
		return new ResponseEntity(friendships, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		Optional<Friendship> optionalFriendship = friendshipService.getById(id);

		if (optionalFriendship.isPresent()) {
			return new ResponseEntity(optionalFriendship.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity create(@RequestBody Friendship friendship) {
		Friendship createdFriendship = friendshipService.create(friendship);
		return new ResponseEntity(createdFriendship, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		friendshipService.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}

