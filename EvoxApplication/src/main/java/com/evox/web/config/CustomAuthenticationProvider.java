package com.evox.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.evox.web.model.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	  // API

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if (name.equals("admin") && password.equals("system")) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//            final User principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
	
	
	
//	@Autowired 
//	private UserServices userService;
//	
//	@Override
//	public Authentication authenticate(Authentication authentication)
//			throws AuthenticationException {
//		
//		// Catch the user name
//		String userName = (String) authentication.getPrincipal();
//		
//		System.out.println("------------------------- > > > > >  " + userName);
//		
//		// Catch the password
//		String password = (String) authentication.getCredentials();
//		
//		User user = (User) userService.findUserByUserName(userName);
//		
//		if(user == null){
//			
//			throw new BadCredentialsException("[CustomAuthenticationProvider] "
//					+ "Authentication Failed!!! Reason: User is not exist: [" + userName + "]");
//		}
//		 if (user.getPassword().equals(password)) {
//		        System.out.println("[CustomAuthenticationProvider] - Authentication Success!!!");
//
//		        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//		        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//		        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userName, password, roles);
//		            result.setDetails(user);
//		            return result;
//		        } else {
//		            throw new BadCredentialsException("[CustomAuthenticationProvider] Authentication Failed!!! Reason: Wrong password");
//		  }
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
//	}

}
