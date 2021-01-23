package com.example.springredditclone.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.mapper.SubredditMapper;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.SubredditJpaRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
	
	private SubredditJpaRepository subredditRepo;
	private SubredditMapper subredditMapper;
	
	@Transactional
	public SubredditDto save(SubredditDto subredditDto){
		Subreddit save = subredditRepo.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		log.info("subreddit: " + save);
		subredditDto.setId(save.getId());
		return subredditDto;
	}
	
	
	@Transactional
	public List<SubredditDto> getAllSubreddits() {
		return subredditRepo.findAll().stream().map(subredditMapper::mapSubredditToDto).collect(Collectors.toList());
	}

	
	@Transactional
	public SubredditDto getSubreddit(Long id) {
		Optional<Subreddit> subreddit = subredditRepo.findById(id);
		subreddit.orElseThrow(() -> new SpringRedditException("Subreddit with id: " + id + " doesn't exists."));
		return subredditMapper.mapSubredditToDto(subreddit.get());
	}
	
	
}
