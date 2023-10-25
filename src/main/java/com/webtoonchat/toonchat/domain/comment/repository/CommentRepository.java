package com.webtoonchat.toonchat.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtoonchat.toonchat.domain.comment.entity.Comment;



public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByBoardId(Long boardId);

}

