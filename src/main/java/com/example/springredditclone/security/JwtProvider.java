package com.example.springredditclone.security;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static io.jsonwebtoken.Jwts.parser;


@Service
public class JwtProvider {
	
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;
	
	@Value("${jwt.secretkey}")
	private String SECRET_KEY;
	
	@SuppressWarnings("deprecation")
	public String generateToken(Authentication authentication){
		User user = (User) authentication.getPrincipal();
		return Jwts.builder()
							.setSubject(user.getUsername())
							.setIssuedAt(Date.from(Instant.now()))
							.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
							.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET_KEY.getBytes()))
							.compact();
	}
	
	public Boolean validateToken(String jwt){
			parser().setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes())).parseClaimsJws(jwt);
			return true;
	}
	
	public String getUsernameFromJwt(String jwt){
		Claims claim = parser().setSigningKey(Base64.getEncoder().encode(SECRET_KEY.getBytes())).parseClaimsJws(jwt).getBody();
		return claim.getSubject();
	}

}
