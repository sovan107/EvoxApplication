package com.evox.web.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.evox.web.config.filters.JWTFilter;
import com.evox.web.dao.UserRepository;
import com.evox.web.model.User;
import com.evox.web.model.UserRole;
import com.evox.web.services.impl.TokenAuthenticationService;

@Configuration
public class WebConfiguration {

	@Autowired
	TokenAuthenticationService taService;
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JWTFilter(taService));
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}
	@Bean
	public InitializingBean insertDefaultUsers() {
		return new InitializingBean() {
			@Autowired
			private UserRepository userRepository;

			@Override
			public void afterPropertiesSet() {
				addUser("admin", "admin");
				addUser("user", "user");
			}

			private void addUser(String username, String password) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(new BCryptPasswordEncoder().encode(password));
//				user.setPassword(password);
				user.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
				userRepository.save(user);
			}
		};
	}
}
