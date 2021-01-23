package com.example.springredditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {
	
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(target = "description", source = "postRequest.description")
	Post map(PostRequest postRequest, Subreddit subreddit, User user);
	
	@Mapping(target = "postId", source = "postId")
	@Mapping(target = "postName", source = "postName")
	@Mapping(target = "subredditName", source = "subreddit.name")
	@Mapping(target = "userName", source = "user.username")
	PostResponse mapToDto(Post post);
	
	
}
