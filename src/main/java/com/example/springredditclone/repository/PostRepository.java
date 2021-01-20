package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springredditclone.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
