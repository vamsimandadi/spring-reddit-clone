package com.example.springredditclone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.service.SubredditService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {
	
	private SubredditService subredditService;
	
	@PostMapping
	public ResponseEntity<SubredditDto> saveSubreddit(@RequestBody SubredditDto subredditDto){
			SubredditDto subreddit = subredditService.save(subredditDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(subreddit);
	}
	
	@GetMapping
	public ResponseEntity<List<SubredditDto>> getAllSubreddits(){
		return ResponseEntity.status(HttpStatus.OK).body(subredditService.getAllSubreddits());
	}
	
	@GetMapping("/{id}")
	public SubredditDto getSubreedit(@PathVariable Long id){
		return subredditService.getSubreddit(id);
	}

}
