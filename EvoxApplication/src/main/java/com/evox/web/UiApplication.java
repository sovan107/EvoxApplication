package com.evox.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.evox.web.config.filters.JWTFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class UiApplication {

	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JWTFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
}
