package com.evox.web.config.handlers;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.evox.web.model.User;
import com.evox.web.services.impl.UserDetailsService;

public final class TokenHandler {
	
	private final String secret;
	
	private final UserDetailsService userService;

	public TokenHandler(String secret, UserDetailsService userService) {
		
	    this.secret = secret;
	    this.userService = userService;
	}
	
	public User parseUserFromToken(String token) {
		
	    String username = Jwts.parser()
	            .setSigningKey(secret)
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	    
	    return userService.loadUserByUsername(username);
	}
	
	public String createTokenForUser(User user) {
		
	    return Jwts.builder()
				.setSubject(user.getUsername())
	            .claim("roles", "Hello World")
	            .signWith(SignatureAlgorithm.HS256, secret).compact();
	}
}
