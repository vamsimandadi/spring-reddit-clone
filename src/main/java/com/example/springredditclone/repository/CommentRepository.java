package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
