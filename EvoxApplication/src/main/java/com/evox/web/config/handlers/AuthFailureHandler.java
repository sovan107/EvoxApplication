package com.evox.web.config.handlers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.evox.web.exceptions.ResponseExceptionHandler;

/**
 * An authentication failure handler that sets the response status 401
 * UNAUTHORIZED and prints the error message.
 * 
 */
@Component
public class AuthFailureHandler extends ResponseExceptionHandler implements
		AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		resolveException(request, response, exception);
	}
}