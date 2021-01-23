package com.example.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	
	private Long postId;
	private String postName;
	private String description;
	private String url;
	private String subredditName;
	private String userName;
}
