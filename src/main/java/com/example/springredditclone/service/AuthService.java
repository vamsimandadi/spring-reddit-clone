package com.example.springredditclone.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springredditclone.dto.AuthenticationResponse;
import com.example.springredditclone.dto.LoginRequest;
import com.example.springredditclone.dto.RegisterRequest;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.VerificationToken;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.repository.VerificationTokenJpaRepository;
import com.example.springredditclone.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenJpaRepository verificationTokenJpaRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtprovider;
	
	
	@Transactional
	public void signup(RegisterRequest registerRequest){
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassowrd( passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate Your Account", user.getEmail(), "Thank you for signing up to Spring Reddit, please click on the below url to activate your account : http://localhost:8080/api/auth/accountVerification/" + token));
	}
	
		public String generateVerificationToken(User user){
			String token = UUID.randomUUID().toString();
			VerificationToken verificationToken = new VerificationToken();
			verificationToken.setToken(token);
			verificationToken.setUser(user);
			
			verificationTokenJpaRepository.save(verificationToken);
			
			return token;
			
		}
		
		@Transactional
		public void verifyAccount(String token) {
				Optional<VerificationToken> verificationToken = verificationTokenJpaRepository.findByToken(token);
				verificationToken.orElseThrow(() -> new SpringRedditException("invalid token"));
				fetchUserAndEnable(verificationToken.get());
		}
		
		public void fetchUserAndEnable(VerificationToken verificationToken){
				String username = verificationToken.getUser().getUsername();
				User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - "+username));
				user.setEnabled(true);
				userRepository.save(user);
				
		}
		

		public AuthenticationResponse login(LoginRequest loginRequest) {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication); //checks if the user is login or not
				String token =  jwtprovider.generateToken(authentication);
				return new AuthenticationResponse(token, loginRequest.getUsername());
		}

		
}
