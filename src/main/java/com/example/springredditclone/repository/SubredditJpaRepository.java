package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springredditclone.model.Subreddit;

@Repository
public interface SubredditJpaRepository extends JpaRepository<Subreddit, Long>{

}
