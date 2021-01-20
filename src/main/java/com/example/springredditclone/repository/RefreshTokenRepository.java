package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

}
