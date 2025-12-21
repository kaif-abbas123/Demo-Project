package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {

		var user = User.withUsername("user").password("{noop}1234") // <-- important!
				.roles("USER").build();

		var admin = User.withUsername("admin").password("{noop}1234").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/public").permitAll().anyRequest()
				.authenticated().and().httpBasic();

		return http.build();
	}
}
