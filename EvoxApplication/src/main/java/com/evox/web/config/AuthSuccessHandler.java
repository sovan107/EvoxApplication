
package com.evox.web.config;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * An authentication success handler that simply sets the response status 200
 * OK. Also clears AUTHENTICATION_EXCEPTION which may have been stored in the
 * session during the authentication process
 * 
 * @author Kaushik Sahoo
 */
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		response.setStatus(HttpServletResponse.SC_OK);
		clearAuthenticationAttributes(request);
	}
}