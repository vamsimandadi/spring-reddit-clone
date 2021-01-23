package com.example.springredditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springredditclone.model.Subreddit;

@Repository
public interface SubredditJpaRepository extends JpaRepository<Subreddit, Long>{

	Optional<Subreddit> findByName(String subredditName);

}
