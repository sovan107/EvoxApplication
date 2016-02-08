package com.evox.web.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import com.evox.web.model.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles response exception and sends a JSON response with the status code.
 * 
 * @author Kaushik Sahoo
 */
public class ResponseExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Writes exception message to response as JSON
	 * @param request
	 * @param response
	 * @param ex
	 * @throws IOException
	 */
	protected void resolveException(HttpServletRequest request,
			HttpServletResponse response, Exception ex) throws IOException {
		
		logger.error(ex.getMessage(), ex);
		
		response.setContentType("application/json");

		if (ex instanceof APIException) {
			APIException exception = (APIException) ex;
			response.setStatus(exception.getHttpStatus().value());
		} else if (ex instanceof AccessDeniedException) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else if (ex instanceof AuthenticationException) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		new ObjectMapper().writeValue(response.getOutputStream(),
				new ErrorDTO(ex.getMessage()));
	}
}