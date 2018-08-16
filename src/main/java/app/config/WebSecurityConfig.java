package app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import app.CustomUserDetails;
import app.entity.Role;
import app.entity.User;
import app.repository.UserRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	UserRepository repo;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(username -> new CustomUserDetails(repo.findByUsername(username)));
		
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		if (repo.count() == 0) {
			Role roles[] = new Role[] { new Role("LIBRARIAN"), new Role("BORROWER") };
			repo.save(new User("user", "user@mail.com", "user", Arrays.asList(roles)));
		}

		return super.authenticationManagerBean();
	}
}