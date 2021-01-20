package com.example.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springredditclone.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
