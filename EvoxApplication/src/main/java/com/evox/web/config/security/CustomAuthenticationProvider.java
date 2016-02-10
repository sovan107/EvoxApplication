package com.evox.web.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.evox.web.model.User;
import com.evox.web.services.UserServices;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired 
	private UserServices userService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		// Catch the user name
		String userName = (String) authentication.getPrincipal();
		System.out.println("------------------------- > > > > >  " + userName);
		
		// Catch the password
		String password = (String) authentication.getCredentials();
		
		User user = (User) userService.findUserByUserName(userName);
		
		if(user == null){
			
			throw new BadCredentialsException("[CustomAuthenticationProvider] "
					+ "Authentication Failed!!! Reason: User is not exist: [" + userName + "]");
		}
		 if (user.getPassword().equals(password)) {
		        System.out.println("[CustomAuthenticationProvider] - Authentication Success!!!");

		        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userName, password, roles);
		            result.setDetails(user);
		            return result;
		        } else {
		            throw new BadCredentialsException("[CustomAuthenticationProvider] Authentication Failed!!! Reason: Wrong password");
		  }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
