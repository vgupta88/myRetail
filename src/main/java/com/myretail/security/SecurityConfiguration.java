package com.myretail.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("testadmin").password(encoder().encode("adminsecret")).roles("ADMIN")
		.and()
		.withUser("testuser").password(encoder().encode("usersecret")).roles("USER");
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.httpBasic().and().authorizeRequests().antMatchers("/products/**")
		.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and()
		.csrf().disable().headers().frameOptions().disable();
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
