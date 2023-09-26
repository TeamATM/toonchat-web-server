package com.webtoonchat.toonchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
