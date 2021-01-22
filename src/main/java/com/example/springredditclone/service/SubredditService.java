package com.example.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.SubredditJpaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
	
	private SubredditJpaRepository subredditRepo;
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto){
		Subreddit save = subredditRepo.save(mapSubredditDto(subredditDto));
		log.info("subreddit: " + save);
		subredditDto.setId(save.getId());
		return subredditDto;
	}
	
	private Subreddit mapSubredditDto(SubredditDto subredditDto){
			 return Subreddit.builder().name(subredditDto.getName()).description(subredditDto.getDescription()).build();
	}	
	
	@Transactional
	public List<SubredditDto> getAllSubreddits() {
		return subredditRepo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	private SubredditDto mapToDto(Subreddit subreddit){
		return SubredditDto.builder().id(subreddit.getId()).name(subreddit.getName()).description(subreddit.getDescription()).numberOfPosts(subreddit.getPosts().size()).build();
	}
}
