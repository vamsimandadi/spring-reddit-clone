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

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post")
public class PostController {

	private PostService postService;

	@PostMapping
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequest));
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> getPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));

	}

	@GetMapping("/by-subreddit/{id}")
	public List<PostResponse> getPostsBySubreddit(@PathVariable Long id) {
		return postService.getPostsBySubreddit(id);
	}
	
	@GetMapping("/by-user/{id}")
	public List<PostResponse> getPostsByUsername(@PathVariable String username){
		return postService.getPostsByUsername(username);
	}

}
