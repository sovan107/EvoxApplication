
package com.evox.web.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.evox.web.model.LoginResponse;
import com.evox.web.services.UserServices;

/**
 * An authentication success handler that simply sets the response status 200
 * OK. Also clears AUTHENTICATION_EXCEPTION which may have been stored in the
 * session during the authentication process
 * 
 */
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserServices userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
	LoginResponse token = new LoginResponse(Jwts.builder()
				.setSubject(authentication.getName())
	            .claim("roles", "Hello World")
	            .setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, "secretKey").compact());
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		response.getWriter().write(token.getToken());
		
		clearAuthenticationAttributes(request);
	}
}