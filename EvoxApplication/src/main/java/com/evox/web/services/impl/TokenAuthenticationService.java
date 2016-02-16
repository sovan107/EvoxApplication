package com.evox.web.services.impl;

import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.evox.web.config.handlers.TokenHandler;
import com.evox.web.config.security.UserAuthentication;
import com.evox.web.model.User;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_HEADER_NAME = "Authorization";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret, UserDetailsService userService) {
		tokenHandler = new TokenHandler(secret, userService);
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws IOException {
		
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + TEN_DAYS);
		
		response.addHeader("Authorization", tokenHandler.createTokenForUser(user));
		response.getWriter().write(tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) throws ServletException {
		
		final String authHeader = request.getHeader(AUTH_HEADER_NAME);
        
		if (authHeader != null) {
			
			if (!authHeader.startsWith("Bearer ")) {
				
	            throw new ServletException("Missing or invalid Authorization header.");
	        }
			
			final String token = authHeader.substring(7);
			
			System.out.println("Token : " +token);
			 try {
				 
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					
					return new UserAuthentication(user);
				}
			}catch (final SignatureException e) {
				
	            throw new ServletException("Invalid token.");
	        }
			
		}
		return null;
	}
}

