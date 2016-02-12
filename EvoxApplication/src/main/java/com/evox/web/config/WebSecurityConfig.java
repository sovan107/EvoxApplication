package com.evox.web.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.evox.web.config.filters.AuthFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private AuthenticationProvider authenticationProvider;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/index.html", "/home.html", "/login.html", "/", "/signup.html").permitAll()
                .antMatchers("/js/**", "/css/**", "/favicon.ico").permitAll()
            .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedPage("/errors/access-denied.html")
            .and()
                .csrf()
				.csrfTokenRepository(csrfTokenRepository()).and()
                .logout().logoutSuccessUrl("/login.html?logout").and()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
		
//		http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class);
		http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		 auth.authenticationProvider(authenticationProvider);
		 		
	 }
	 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	 
	 @Bean
	public AuthFilter authFilter() throws Exception {
		AuthFilter authFilter = new AuthFilter();
		authFilter.setAuthenticationManager(authenticationManager());
		authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		
		return authFilter;
	}
	 
	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				
				if (csrf != null) {
					
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null
							&& !token.equals(cookie.getValue())) {
						
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
	}
}
