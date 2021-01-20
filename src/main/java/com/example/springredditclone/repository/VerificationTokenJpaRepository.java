package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.VerificationToken;

public interface VerificationTokenJpaRepository extends JpaRepository<VerificationToken, Long>{

}
