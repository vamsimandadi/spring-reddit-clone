package com.example.springredditclone.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exception.PostNotFoundException;
import com.example.springredditclone.exception.SubredditNotFoundException;
import com.example.springredditclone.exception.UserNotFoundException;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.SubredditJpaRepository;
import com.example.springredditclone.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
	
	private SubredditJpaRepository subredditRepo;
	private PostRepository postRepo;
	private PostMapper postMapper;
	private AuthService authService;
	private UserRepository userRepo;
	
	public PostResponse save(PostRequest postRequest) {
		Subreddit subreddit = subredditRepo.findByName(postRequest.getSubredditName())
													 .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
		 User currentUser = authService.getCurrentUser();
		 Post savedPost = postRepo.save(postMapper.map(postRequest, subreddit, currentUser));
		 return postMapper.mapToDto(savedPost);
	}
	
	public List<PostResponse> getPosts(){
		return postRepo.findAll()
						.stream()
						.map(postMapper::mapToDto)
						.collect(Collectors.toList());
	}
	
	
	public List<PostResponse> getPostsBySubreddit(Long id) {
		Subreddit subreddit = subredditRepo.findById(id).orElseThrow(() -> new SubredditNotFoundException("Doesn't exists Subreddit with id: " + id));
		return postRepo.findBySubreddit(subreddit).stream().map(postMapper::mapToDto).collect(Collectors.toList());						
	}
	
	public List<PostResponse> getPostsByUsername(String username){
		User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		return postRepo.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}

	public PostResponse getPost(Long id) {
		Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("No post with id: " + id));
		return postMapper.mapToDto(post);
	}	
	
}
