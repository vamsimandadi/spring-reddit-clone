package com.example.springredditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;

public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findBySubreddit(Subreddit subreddit);

	List<Post> findByUser(User user);

}
