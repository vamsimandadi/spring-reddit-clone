package com.example.springredditclone.exception;

public class SubredditNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2935387864584787876L;

	public SubredditNotFoundException(String message){
		super(message);
	}
}
