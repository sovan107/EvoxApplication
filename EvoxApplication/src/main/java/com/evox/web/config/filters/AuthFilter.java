package com.evox.web.config.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.Validator;

import com.evox.web.config.security.UserAuthentication;
import com.evox.web.model.User;
import com.evox.web.services.impl.TokenAuthenticationService;
import com.evox.web.services.impl.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Custom Authentication filter. This extends the
 * {@link UsernamePasswordAuthenticationFilter} but the username and password is
 * expected as JSON. A token is added to the headers for maintaining multiple
 * clients. <br>
 * <br>
 * The login url is <code>/login</code> <br>
 * <br>
 * Expected JSON format for login is
 * 
 * <pre>
 * 	{
 * 		"username":"username",
 * 		"password":"password"
 * 	}
 * </pre>
 * 
 */
public class AuthFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private Validator validator;
	
	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userDetailsService;
	/**
	 * The constructor sets the login URL. The login url is <code>/login</code><br>
	 * The accepted http method is <code>POST</code>
	 */
	public AuthFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			UserDetailsService userDetailsService) {
		
		super(new AntPathRequestMatcher(urlMapping, "POST"));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an Authentication for it
		final User authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
}