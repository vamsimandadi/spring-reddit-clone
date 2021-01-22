package com.example.springredditclone.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.UserRepository;

import lombok.AllArgsConstructor;
import static java.util.Collections.singletonList;


@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService{

	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user with username - " + username));
		return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassowrd(),
                user.getEnabled(), true, true,
                true, getAuthorities("USER"));
	}
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}

}
