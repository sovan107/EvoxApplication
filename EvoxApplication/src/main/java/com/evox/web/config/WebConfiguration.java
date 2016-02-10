package com.evox.web.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evox.web.config.filters.JWTFilter;

@Configuration
public class WebConfiguration {

	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JWTFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
