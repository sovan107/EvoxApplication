package com.evox.web.config.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import com.evox.web.exceptions.APIException;
import com.evox.web.exceptions.ExceptionMessages;
import com.evox.web.model.UserModel;
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
	
	/**
	 * The constructor sets the login URL. The login url is <code>/login</code><br>
	 * The accepted http method is <code>POST</code>
	 */
	public AuthFilter() {
		super(new AntPathRequestMatcher("/user", "POST"));
	}

	/**
	 * Attempts authentication by parsing the JSON login object and creating an
	 * authentication token which is passed on to authentication manager.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		UserModel user;

		try {
			user = new ObjectMapper().readValue(request.getReader(), UserModel.class);
		} catch (Exception e) {
			throw new APIException(ExceptionMessages.INVALID_LOGIN_JSON,
					HttpStatus.BAD_REQUEST);
		}

		// If LoginDTO has validation errors
		BindException bindException = new BindException(user, "User Model");
		validator.validate(user, bindException);
		if (bindException != null && bindException.hasErrors()) {
			throw new APIException(bindException.getFieldError().getDefaultMessage(),
					HttpStatus.BAD_REQUEST);
		}
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				user.getUserName().trim(), user.getPassword());

		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}