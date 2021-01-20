package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

}
