package com.example.springredditclone.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;


@Mapper(componentModel = "spring")
public interface SubredditMapper {
	
//	SubredditMapper INSTANCE = Mappers.getMapper( SubredditMapper.class );
	Subreddit subreddit = new Subreddit();
		
	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
	public SubredditDto mapSubredditToDto(Subreddit subreddit);
	
	default Integer mapPosts(List<Post> numberOfPosts){
		return numberOfPosts.size();
	}
	
	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	public Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
