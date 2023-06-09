package com.example.demo.config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((authorize) ->
		// authorize.anyRequest().authenticated()
		authorize.antMatchers(HttpMethod.GET, "/movie").hasRole("USER").antMatchers(HttpMethod.POST, "/movie")
				.hasRole("USER"));

		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests().antMatchers("/h2-console/**").permitAll();
		http.headers(headers -> headers.frameOptions().disable());

		return http.build();
		// =============

	}
}
