package com.evox.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.evox.web.config.filters.AuthFilter;
import com.evox.web.services.impl.TokenAuthenticationService;
import com.evox.web.services.impl.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private AuthenticationProvider authenticationProvider;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/index.html", "/home.html", "/login.html", "/", "/signup.html").permitAll()
                .antMatchers("/js/**", "/css/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedPage("/errors/access-denied.html")
            .and()
               .csrf().disable();
            
		
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// custom Token based authentication based on the header previously given to the client
//		http.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}

	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		 auth.authenticationProvider(authenticationProvider);
		 auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		 		
	 }
	 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	 
	@Bean
	public AuthFilter authFilter() throws Exception {
		 
		AuthFilter authFilter = new AuthFilter("/user", tokenAuthenticationService,userDetailsService);
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		
		return authFilter;
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
	}
}
